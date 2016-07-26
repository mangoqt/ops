package com.qt.xxtmonitor.handler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.qt.core.command.ExecuteResult;
import com.qt.core.command.LocalCommandExecutorService;
import com.qt.core.command.LocalCommandExecutorServiceImpl;
import com.qt.core.util.CommandUtil;
import com.qt.core.util.CompareUtil;
import com.qt.xxtmonitor.model.ScriptMonitor;
import com.qt.xxtmonitor.model.MonitorObj;

/**
 * @author Tim
 *
 */
@Component(value="scriptMonitorHandler")
public class ScriptMonitorHandler extends MonitorHandler{
	
	private static final Log loggerInfo = LogFactory.getLog("INFO."+ScriptMonitorHandler.class.getName());
	private static final Log logger = LogFactory.getLog(ScriptMonitorHandler.class.getName());

	/* (non-Javadoc)
	 * @see com.qt.xxtmonitor.handler.MonitorHandler#process(java.util.List)
	 */
	@Override
	public void process(final List<MonitorObj> list) throws MonitorException{
		for(MonitorObj obj: list){
			long curTime = System.currentTimeMillis();
			ScriptMonitor logMonitor = (ScriptMonitor)obj;

			/*
			String command = "ssh "+ logMonitor.getServerIp() + " tail -1 "+logMonitor.getLogFullName();
			String command1 = "ssh "+ logMonitor.getServerIp() + " ls -l --time=atime --full-time "
				+logMonitor.getLogFullName() + "| awk -F\" \" '{print $6 \" \" $7}'";
			String monitorContent = CommandUtil.exec(command);
			String lastUtime = CommandUtil.exec(command1);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSSSSS");
			Date uDate = sdf.parse(lastUtime);
			Date curDate = new Date();
			
			if(curDate.getTime() - uDate.getTime() > logMonitor.getMonitorInterval() * 1000){
				logMonitor.setError(true);
			}
			
			if(!monitorContent.trim().contains(logMonitor.getMonitorContent())){
				logMonitor.setError(true);
			}*/
//				String result = CommandUtil.exec(new String [] {"sh","-c",logMonitor.getScript()});
			LocalCommandExecutorService service = new LocalCommandExecutorServiceImpl();
			ExecuteResult executeResult = service.executeCommand(new String [] {"sh","-c",logMonitor.getScript()}, 10000);
			String result;
			if(executeResult.getExitCode() == -2){
				result = "timeout";
			}else{
				result = executeResult.getExecuteOut();
			}
			boolean flag = CompareUtil.compare(result, logMonitor.getMonitorContent(),logMonitor.getCompareParam());
			if(flag){
				logMonitor.setError(false);
			}else{
				logMonitor.setWarnMsg(logMonitor.getWarnMsg()+":"+(result.length()<15?result:result.substring(0,15)));
			}
			Date date = new Date();
			
			loggerInfo.info(logMonitor.getScript()+" : "+ result.trim() + " : "+logMonitor.getRemark() +" : " + (date.getTime()-curTime));
		}
	}
	
	public List<MonitorObj> getMonitorList(int warnInterval){
		String sql = "select type,monitor_content,warn_interval,"
				+ "warn_msg,remark,phones,script,compareParam from SUP_MON_SCRIPT where enable=1 and warn_interval=?";
		
		List<MonitorObj> list = getJdbcTemplate().query(sql, new Object[]{warnInterval},new RowMapper<MonitorObj>(){

			public MonitorObj mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				ScriptMonitor obj = new ScriptMonitor();
				obj.setMonitorContent(rs.getString("monitor_content"));
				obj.setMonitorInterval(rs.getInt("warn_interval"));
				obj.setWarnMsg(rs.getString("warn_msg"));
				obj.setRemark(rs.getString("remark"));
				obj.setPhones(rs.getString("phones"));
				obj.setCompareParam(rs.getString("compareParam"));
				obj.setScript(rs.getString("script"));
				return obj;
			}
			
		});
		
		return list;
	}
}
