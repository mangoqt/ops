package com.qt.xxtmonitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import qtone.xxt.smsmq.message.groupsend.XxtMessage;

import com.qt.core.container.SpringBeanUtil;
import com.qt.core.util.PropHandler;
import com.qt.core.util.SmsUtil;
import com.qt.core.util.WeixinAPIHelper;
import com.qt.xxtmonitor.handler.MonitorHandler;
import com.qt.xxtmonitor.model.MonitorObj;
import com.qt.xxtmonitor.model.SmsGatewayInfo;

/**
 * @author Tim
 * 
 */
@Component(value = "monitorService")
public class MonitorService {

	private static final Log infoLogger = LogFactory.getLog("INFO."+ MonitorService.class.getName());
	private static final Log logger = LogFactory.getLog(MonitorService.class.getName());

	@Resource(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate;

	public void monitor5(){
		startMonitor(5);
	}
	
	public void monitor10(){
		startMonitor(10);
	}
	
	public void monitor30(){
		startMonitor(30);
	}
	
	public void startMonitor(int warnInterval) {
//		System.out.println("start Monitor! warnInterval="+warnInterval);
		String[] beans = PropHandler.get("handlers").split(",");
		Map<String, String> map = new HashMap<String, String>();

		for (String beanName : beans) {
			MonitorHandler handler = (MonitorHandler) SpringBeanUtil
					.getBean(beanName);
			List<MonitorObj> list = handler.getMonitorList(warnInterval);
			if (list != null && list.size() > 0) {
				handler.process(list);
			}
			notifyUser(list, map);
		}
		
		Set<String> keys = map.keySet();
		for (String phone : keys) {
			//增加时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
			String dateStr = sdf.format(date);
			String warnContent = map.get(phone)+ " ("+dateStr+")";

			infoLogger.info(phone + "的通知内容:" + warnContent);
			
			WeixinAPIHelper.sendTextMsg(Arrays.asList(new String[]{phone}), warnContent);
			
			/*
			String flag = PropHandler.get("is_cloud");
			if("0".equals(flag)){
				List<SmsGatewayInfo> gatewayList = this.getSmsGatewayInfos();

				for (SmsGatewayInfo info : gatewayList) {
					sendSms(info, warnContent, phone);
				}
			}else{
				XxtMessage message = new XxtMessage();
				message.setContent(warnContent);
				message.setMobile(phone);
				message.setAreaName("cz");
				message.setMessagecode("22");
				message.setMessageType(21);//监控短信
				message.setFromAppType(11);//监控程序
				
				SmsUtil.getInstance().sendMonitorMessage(message);
			}*/
		}
	}

	private List<SmsGatewayInfo> getSmsGatewayInfos() {
		String[] gateways = PropHandler.get("notify_gateway").split(",");
		String search="(";
		
		for(int i=0; i<gateways.length;i++){
			if(i==0){
				search = search+"'"+gateways[i]+"'";
			}else{
				search = search+",'"+gateways[i]+"'";
			}
		}
		search +=")";
		
		String sql = "select * from sms_gateway_info where name in " +search;
		List<SmsGatewayInfo> list = jdbcTemplate.query(sql, new RowMapper<SmsGatewayInfo>() {

			public SmsGatewayInfo mapRow(final ResultSet rs, final int num) throws SQLException {
				final SmsGatewayInfo gatewayInfo = new SmsGatewayInfo();

				gatewayInfo.setId(rs.getInt("ID"));
				gatewayInfo.setName(rs.getString("NAME"));
				gatewayInfo.setKind(rs.getInt("KIND"));
				gatewayInfo.setMainPort(rs.getInt("MAIN_PORT"));
				gatewayInfo.setAreaRange(rs.getString("AREA_RANGE"));
				gatewayInfo.setDbClass(rs.getString("DB_CLASS"));
				gatewayInfo.setDbJdbc(rs.getString("DB_JDBC"));
				gatewayInfo.setDbUser(rs.getString("DB_USER"));
				gatewayInfo.setDbPws(rs.getString("DB_PWS"));

				return gatewayInfo;
			}

		});
		return list;
	}

	/*private boolean sendSms(SmsGatewayInfo info, String amsg, String sphone) {

		String[] spid = { "419042", "999999" };
		String version = "CSJZFE";

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(info.getDbClass());
		ds.setUrl(info.getDbJdbc());
		ds.setUsername(info.getDbUser());
		ds.setPassword(info.getDbPws());
		Connection conn = null;

		// 提交到网关
		try {
			int step;
			int iNum = 0;
			String a_msg = new String(amsg.getBytes(),"GBK"); // 短信内容

			Helper helper = new Helper();
			String sEnCode = helper.StrEncode(a_msg);
			String a_toCharset = "GBK";
			short a_dcs = 8;
			short a_esm = (short) 64;

			String a_source = "";

			step = 300; // 300个字拆分.

			conn = ds.getConnection();

			int len = a_msg.length();
			long[] ids = new long[len / step + 1];
			for (int i = 0; i < len; i += step) { // 把过多的短信拆分发送
				int j = i + step;
				String str = a_msg.substring(i, j < len ? j : len);

				MessageDAOImpl2 msg = new MessageDAOImpl2();

				msg.setSource(a_source);
				msg.setMessage(str);
				msg.setDCS(a_dcs);
				msg.setESM(a_esm);

				if (info.getMainPort() == 10657061) {
					msg.setSP_ID(spid[1]);
				} else if (info.getMainPort() == 1065827) {
					msg.setSP_ID(spid[0]);
				}

				// 发送时间
				msg.setSchedule(null);
				java.util.Date Expiry = null;
				// 设置过期时间
				msg.setExpiry(Expiry);
				msg.setVersion(version); // 短信发送的扣费标识
				msg.setPrefix("");
				msg.setSender("one"); // - 需要修改?
				msg.setPayment(1); // - 需要修改

//				System.out.println("a_source:" + a_source + ",str:" + str
//						+ ",a_dcs:" + a_dcs + ",a_esm:" + a_esm + ",a_esm:"
//						+ spid + ",spid:" + version);
				msg.applyChanges(conn);
				int l = i / len;
				ids[l] = msg.getMsgID();
//				System.out.println("ids[l]:" + ids[l]);
				iNum++;
				int ri = 0;
				// for (int k = 0; k < mobileTmp.size(); k++) { //
				// 循环所有号码进行发送短信
				ri++;
				// String sMobil = (String) mobileTmp.get(k);
				String sMobil = sphone;
				String sDest = sMobil;
				if (!sMobil.startsWith("86")) {
					sDest = "86" + sMobil;
				}
				try {
					// 提交到短信网关
					DestTableItemDAOImpl2 item = new DestTableItemDAOImpl2();
					item.setMsgID(ids[l]);
					item.setDest(sDest);
					item.setEnCode(sEnCode);
					item.setNum(iNum);
					item.setPriority(9);// 优先级最高
					item.applyChanges(conn);

				} catch (Exception e) {
					logger.error(null,e);
				}
			}
		} catch (Exception e) {
			logger.error(null,e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqle) {
					logger.error(null,sqle);
				}
				conn = null; // The real connection is back in the pool
			}
		}

		return true;

	}*/

	/**
	 * 获取要警告的电话和警告内容
	 * @param list
	 */
	private void notifyUser(final List<MonitorObj> list,
			final Map<String, String> map) {
		for (MonitorObj obj : list) {
			if (obj.isError()) {
				String[] phones = obj.getPhones().split(",");
				for (String phone : phones) {
					if (map.containsKey(phone)) {
						String warnMsgs = map.get(phone);
						if (!warnMsgs.contains(obj.getWarnMsg())) {
							map.put(phone, warnMsgs + ";" + obj.getWarnMsg());
						}
					} else {
						map.put(phone, obj.getWarnMsg());
					}
				}
			}
		}
		
	}
}
