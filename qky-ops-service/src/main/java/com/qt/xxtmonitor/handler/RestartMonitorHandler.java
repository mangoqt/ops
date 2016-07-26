package com.qt.xxtmonitor.handler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.qt.core.command.ExecuteResult;
import com.qt.core.command.SHH2Client;
import com.qt.xxtmonitor.dao.impl.MonRestartDao;
import com.qt.xxtmonitor.model.MonitorObj;
import com.qt.xxtmonitor.model.RestartMonitor;
import com.qt.xxtmonitor.model.ServerLoginInfo;

@Component(value="restartMonitorHandler")
public class RestartMonitorHandler extends MonitorHandler{

	private static final Log loggerInfo = LogFactory.getLog("INFO."
			+ RestartMonitorHandler.class.getName());
	private static final Log logger = LogFactory.getLog(RestartMonitorHandler.class
			.getName());
	
	@Resource(name="monRestartDao")
	private MonRestartDao monRestartDao;
	
	@Value("${key_path}")
	private String keyPath;

	@Override
	public void process(final List<MonitorObj> list)  throws MonitorException{
		for(MonitorObj obj: list){
			long curTime = System.currentTimeMillis();
			RestartMonitor restartMonitor = (RestartMonitor)obj;
			obj.setError(false);
			if(restartMonitor.getIsFault()>=restartMonitor.getFaultCountToRestart()){
				
				obj.setError(true);
				
				ServerLoginInfo info = restartMonitor.getServerLoginInfo();
				
				//暂时只支持public验证登录
				SHH2Client client = new SHH2Client(info.getIp()	, info.getPort(), info.getLoginUser());
				try {
					client.openConnect(keyPath);
					client.addCmd(restartMonitor.getCommand());
					client.addCmd("echo 'finished'");
					ExecuteResult executeResult = client.executeCmd();
					client.closeConnect();
					String result = executeResult.getExecuteOut();
					restartMonitor.setWarnMsg(restartMonitor.getWarnMsg()+":"+(result.length()<15?result:result.substring(0,15)));
			
					Date date = new Date();
					
					loggerInfo.info(restartMonitor.getCommand()+" : "+ result.trim() + " : "+restartMonitor.getRemark() +" : " + (date.getTime()-curTime));
		
				} catch (IOException e) {
					logger.error(null, e);
				}

				//恢复故障次数
				monRestartDao.resumeFault(restartMonitor.getAppName());
				
				/**
				LocalCommandExecutorService service = new LocalCommandExecutorServiceImpl();
				ExecuteResult executeResult = service.executeCommand(new String [] {"sh","-c",restartMonitor.getCommand()}, 40000);
				String result;
//				boolean flag = true;
				if(executeResult.getExitCode() == -2){
					result = "timeout";
				}else{
					result = executeResult.getExecuteOut();
				}
				restartMonitor.setWarnMsg(restartMonitor.getWarnMsg()+":"+(result.length()<15?result:result.substring(0,15)));
//				boolean flag = CompareUtil.compare(result, logMonitor.getMonitorContent(),logMonitor.getCompareParam());
//				restartMonitor.setError(false);
		
				Date date = new Date();
				
				loggerInfo.info(restartMonitor.getCommand()+" : "+ result.trim() + " : "+restartMonitor.getRemark() +" : " + (date.getTime()-curTime));
				**/
			}
			
		}
	}
	
	public List<MonitorObj> getMonitorList(int warnInterval){
		String sql = "select app_name,is_auto_restart,is_fault,"
				+ "command,fault_count_to_restart,phones,warn_msg,warn_interval,remark,server_id,b.ip,b.port,b.is_auth_by_key,b.login_user,b.password "
				+ " FROM SUP_MON_RESTART a LEFT JOIN SUP_SERVER_LOGIN b ON  a.server_id=b.id "
				+ " where is_auto_restart=1 and warn_interval=?";
		
		List<MonitorObj> list = getJdbcTemplate().query(sql, new Object[]{warnInterval},new RowMapper<MonitorObj>(){

			public MonitorObj mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				RestartMonitor obj = new RestartMonitor();
				obj.setAppName(rs.getString("app_name"));
				obj.setIsAutoRestart(rs.getInt("is_auto_restart"));
				obj.setIsFault(rs.getInt("is_fault"));
				obj.setCommand(rs.getString("command"));
				obj.setFaultCountToRestart(rs.getInt("fault_count_to_restart"));
				obj.setMonitorInterval(rs.getInt("warn_interval"));
				obj.setWarnMsg(rs.getString("warn_msg"));
				obj.setRemark(rs.getString("remark"));
				obj.setPhones(rs.getString("phones"));
				obj.setServerId(rs.getString("server_id"));

				ServerLoginInfo info = new ServerLoginInfo();
				info.setIp(rs.getString("ip"));
				info.setIsAuthByKey(rs.getInt("is_auth_by_key"));
				info.setLoginUser(rs.getString("login_user"));
				info.setPort(rs.getInt("port"));
				info.setPassword(rs.getString("password"));
				
				obj.setServerLoginInfo(info);
				
				return obj;
			}
			
		});
		
		return list;
	}

	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}
}
