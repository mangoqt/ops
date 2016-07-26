package com.qt.inspection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.config.RequestConfig;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.qt.core.util.HttpClientUtil;
import com.qt.core.util.IpUtil;
import com.qt.core.util.JsonUtil;
import com.qt.core.util.PropHandler;
import com.qt.core.util.UUIDGenerator;
import com.qt.inspection.dao.impl.DailyCheckDao;
import com.qt.inspection.dao.impl.HostDao;
import com.qt.inspection.model.CheckContent;
import com.qt.inspection.model.CheckContentMain;
import com.qt.inspection.model.CheckContentSub;
import com.qt.inspection.model.CheckInfo;
import com.qt.inspection.model.CpuRsp;
import com.qt.inspection.model.DailyCheck;
import com.qt.inspection.model.DailyCheckRsp;
import com.qt.inspection.model.DfstatRsp;
import com.qt.inspection.model.Host;
import com.qt.inspection.model.LoadAvgRsp;
import com.qt.inspection.model.MemoryRsp;


@Component(value="checkService")
public class CheckService {
	
	private static final Log infoLogger = LogFactory.getLog("INFO."+ CheckService.class.getName());
	private static final Log logger = LogFactory.getLog(CheckService.class.getName());
	
	@Resource(name="hostDao")
	private HostDao hostDao;
	
	@Resource(name="dailyCheckDao")
	private DailyCheckDao dailyCheckDao;
	
	public void sendCheckContent(){
		String flag = PropHandler.get("is_dailycheck");
		String url = PropHandler.get("dailycheck_url");
		if("1".equals(flag)){//开关
			CheckContent content = getCheckContent(PropHandler.get("dailycheck_filename"));
			
			Gson gson = new Gson();
			String reqMsg = gson.toJson(content);
			/*
			List<NameValuePair> list =  new ArrayList<NameValuePair>();
			NameValuePair nvps = new BasicNameValuePair("t_data",reqMsg);
			list.add(nvps);*/
			try {
				RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(10000)
						.setConnectTimeout(10000)
						.setSocketTimeout(10000)
						.build();
				HttpClientUtil.post(url, reqMsg, config);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private CheckContent getCheckContent(String filename){
		CheckContent obj = new CheckContent();
		obj.setSub(getCheckContentSub(filename));
		
		CheckContentMain main = new CheckContentMain();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String curDate = sdf.format(date);
		int hours = date.getHours();
		if(hours > 12){
			main.setAmpm("pm");
		}else{
			main.setAmpm("am");
		}
		main.setCheckTime(curDate);
		//sw-上午 xw-下午
		obj.setMain(main);
		
		return obj;
	}
	
	private CheckContentSub[] getCheckContentSub(String check) {
		File file = new File("upfile"+File.separator+check);
		FileReader fr = null;
		BufferedReader br = null;
		List<CheckContentSub> list = new ArrayList<CheckContentSub>();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String str;
			while ((str=br.readLine()) != null ){
				CheckContentSub content = new CheckContentSub();
				String [] strArray = str.split(",");
				if(strArray.length == 10){
					content.setServerip((strArray[0]==null||"".equals(strArray[0].trim()))?"0":strArray[0]);
					content.setCpuUsage((strArray[1]==null||"".equals(strArray[1].trim()))?"0":strArray[1]);
					content.setLoad((strArray[2]==null||"".equals(strArray[2].trim()))?"0":strArray[2]);
					content.setDiskUsage((strArray[3]==null||"".equals(strArray[3].trim()))?"0":strArray[3]);
					content.setSwap((strArray[4]==null||"".equals(strArray[4].trim()))?"0":strArray[4]);
//  				content.setC_tomcat((strArray[5]==null||"".equals(strArray[5].trim()))?"0":strArray[5]);
//	   	    		content.setC_jboss((strArray[6]==null||"".equals(strArray[6].trim()))?"0":strArray[6]);
		 			content.setProcessNum((strArray[7]==null||"".equals(strArray[7].trim()))?"0":strArray[7]);
		 			content.setMemory((strArray[8]==null||"".equals(strArray[8].trim()))?"0":strArray[8]);
		 			content.setMemoryUsage((strArray[9]==null||"".equals(strArray[9].trim()))?"0":strArray[9]);
					list.add(content);
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(null, e);
		} catch (IOException e) {
			logger.error(null, e);
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					logger.error(null, e);
				}
			}
			if(fr != null){
				try {
					fr.close();
				} catch (IOException e) {
					logger.error(null, e);
				}
			}
		}
		
		CheckContentSub[] contents = new CheckContentSub[list.size()];
		for(int i=0;i<list.size();i++){
			contents[i]=list.get(i);
		}
		return  contents;
	}
	
	public static void main(String [] args){
		CheckContent content = new CheckService().getCheckContent("check.txt");
		Gson gson = new Gson();
		String str = gson.toJson(content);
		
		System.out.println(str);
	}
	
	/**
	 * 同步服务器
	 */
	public void syncHost(){
		List<Host> list = hostDao.findAllFalconHosts();
//		Map<String,Host> map = new HashMap<String,Host>();
		for (Host host : list){
//			map.put(host.getHostName()+"-"+host.getIp(), host);
			Host findHost = hostDao.findHost(host.getHostName(),host.getIp());
			if(findHost != null){
//				hostDao.updateHostSynTime(findHost.getId());
			}else{
				hostDao.saveHost(host);
			}
		}
		
		syncHostUpdateTime();
	}
	
	public void syncHostUpdateTime(){
		List<Host> list = hostDao.findAllEnpoint();
		for (Host host : list){
			Host findHost = hostDao.findHost(host.getHostName());
			if(findHost!=null){
				hostDao.updateHostSynTime(findHost.getId(),host.getSynTm());
			}
		}
	}
	
	/**
	 * 获取服务器信息
	 */
	public void dailyCheck(){
		
		syncHost();
		
		List<Host> list = this.hostDao.findAllHosts();
		String dailyCheckId = UUIDGenerator.getUUID();
		DailyCheck dailyCheck = new DailyCheck();
		dailyCheck.setCheckTm(new Date());
		dailyCheck.setId(dailyCheckId);
		dailyCheckDao.save(dailyCheck);
		
		for(Host host : list){
			if(!"pub".equals(host.getCloudLocation())){
				continue;
			}
			
			if(!IpUtil.isboolIp(host.getIp())){
				logger.error(host.getHostName()+":"+host.getIp()+": ip check fomat failed");
				continue;
			}
			
			CheckInfo info = new CheckInfo();
			info.setId(UUIDGenerator.getUUID());
			info.setDailyCheckId(dailyCheckId);
			info.setHostName(host.getHostName());
			info.setIp(host.getIp());
			String baseUrl = "http://"+host.getIp()+":1988";
			DecimalFormat df=new DecimalFormat("000.00");
			
			//获取内存
			String str = "";
			try {
				str = HttpClientUtil.post(baseUrl+"/proc/memory", "",null);
			} catch (IOException e) {
				logger.error("", e);
			}
			MemoryRsp rsp = (MemoryRsp) JsonUtil.getInstance().json2obj(str, MemoryRsp.class);
			if(rsp != null){
				info.setMemFree(df.format((double)rsp.getData().getFree()/1024/1024/1024) + "G"); 
				info.setMemUsageRate(df.format((double)rsp.getData().getUsed()/rsp.getData().getTotal()*100)+"%");
			}
			
			//获取cpu
			try {
				str = HttpClientUtil.post(baseUrl+"/proc/cpu/usage", "",null);
			} catch (IOException e) {
				logger.error("", e);
			}
			CpuRsp rsp1 = (CpuRsp) JsonUtil.getInstance().json2obj(str, CpuRsp.class);
			if(rsp != null){
				info.setCpuUsage(df.format(rsp1.getData().getBusy())+"%");
			}
			
			
			//获取负载
			try {
				str = HttpClientUtil.post(baseUrl+"/proc/system/loadavg", "",null);
			} catch (IOException e) {
				logger.error("", e);
			}
			LoadAvgRsp rsp2 = (LoadAvgRsp) JsonUtil.getInstance().json2obj(str, LoadAvgRsp.class);
			if(rsp2 != null){
				info.setLoad5Min(rsp2.getData().getAvg5min()+"");
			}
			
			//获取系统时间
			try {
				str = HttpClientUtil.post(baseUrl+"/system/date", "",null);
			} catch (IOException e) {
				logger.error("", e);
			}
			DailyCheckRsp rsp3 = (DailyCheckRsp) JsonUtil.getInstance().json2obj(str, DailyCheckRsp.class);
			if(rsp3 != null){
				info.setServerTime(rsp3.getData());
			}
			
			//获取服务器启动时间
			try {
				str = HttpClientUtil.post(baseUrl+"/page/system/uptime", "",null);
			} catch (IOException e) {
				logger.error("", e);
			}
			DailyCheckRsp rsp4 = (DailyCheckRsp) JsonUtil.getInstance().json2obj(str, DailyCheckRsp.class);
			if(rsp4 != null){
				info.setUptime(rsp4.getData());
			}
			
			//获取df
			try {
				str = HttpClientUtil.post(baseUrl+"/page/df", "",null);
			} catch (IOException e) {
				logger.error("", e);
			}
			StringBuffer dfSb = new StringBuffer();
			DfstatRsp dfstatRsp = (DfstatRsp)JsonUtil.getInstance().json2obj(str, DfstatRsp.class);
			
			if(dfstatRsp != null){
				DecimalFormat decimalFormat = new DecimalFormat("###.0%");
				Number maxN = 0;
				
				for(int i=0; i<dfstatRsp.getData().length;i++){
					String [] df1 = dfstatRsp.getData()[i];
					if(df1.length>6){
						if(df1[5].contains("docker")){
							continue;
						}
						dfSb.append("[");
						dfSb.append(df1[0]).append(",");
						dfSb.append(df1[1]).append(",");
						dfSb.append(df1[2]).append(",");
						dfSb.append(df1[3]).append(",");
						dfSb.append(df1[4]).append(",");
						dfSb.append(df1[5]);
						dfSb.append("]  ");
						dfSb.append("\r\n");
						
						try {
							Number m = decimalFormat.parse(df1[4]);
							if(m.doubleValue()>maxN.doubleValue()){
								maxN = m;
							}
						} catch (ParseException e) {
							logger.error("", e);
						}
						
					}
				}
				info.setDfUsage(dfSb.toString());
				info.setDfMaxUsage(maxN.doubleValue());
			}
			
			dailyCheckDao.save(info);
		}
	}
}
