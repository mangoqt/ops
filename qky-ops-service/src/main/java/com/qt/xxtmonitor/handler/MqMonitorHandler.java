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

import com.qt.core.util.CompareUtil;
import com.qt.core.util.MqConnectionUtil;
import com.qt.xxtmonitor.model.MqConn;
import com.qt.xxtmonitor.model.MqMonitor;
import com.qt.xxtmonitor.model.ScriptMonitor;
import com.qt.xxtmonitor.model.MonitorObj;
import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;

/**
 * @author Tim
 *
 */
@Component(value="mqMonitorHandler")
public class MqMonitorHandler extends MonitorHandler{
	
	private static final Log loggerInfo = LogFactory.getLog("INFO."+MqMonitorHandler.class.getName());
	private static final Log logger = LogFactory.getLog(MqMonitorHandler.class.getName());

	/* (non-Javadoc)
	 * @see com.qt.xxtmonitor.handler.MonitorHandler#process(java.util.List)
	 */
	@Override
	public void process(final List<MonitorObj> list) throws MonitorException{
		
			for(MonitorObj obj: list){
				MqMonitor mqMonitor = (MqMonitor)obj;
				mqMonitor.setError(false);
				String warnQueue="";
				
				String[] queues = mqMonitor.getQueueNames().split(",");
				long curTime = System.currentTimeMillis();
				Channel channel = null;
				String key = "";
				try {
					key = mqMonitor.getMqConn().getHost()+mqMonitor.getMqConn().getPort();
					
					MqConnectionUtil.getInstance().initConnection(mqMonitor.getMqConn());
					
					for(String queueName:queues){
						channel = MqConnectionUtil.getInstance().getChannel(key, queueName, true);
						DeclareOk declareOk = channel.queueDeclare(queueName, true, false,
								false, null);
						int msgCount = declareOk.getMessageCount();
						
						//结果 compareparam 
						boolean tempFlag = CompareUtil.compare(msgCount, mqMonitor.getMonitorContent(), mqMonitor.getCompareParam());
						
						if(!tempFlag){
							warnQueue=warnQueue+"|"+queueName+":"+msgCount;
							mqMonitor.setError(true);
						}
						if(channel != null && channel.isOpen()){
							channel.close();
						}
					}
				} catch (IOException e) {
					logger.error(null,e);
					mqMonitor.setError(true);
					warnQueue=warnQueue+"|MQ连接异常|";
				}finally{
					if(channel != null && channel.isOpen()){
						try {
							channel.close();
						} catch (IOException e) {
							logger.error(null,e);
						}
					}
					MqConnectionUtil.getInstance().closeConn(key);
				}
				
				
				if(mqMonitor.isError()){
					mqMonitor.setWarnMsg(mqMonitor.getWarnMsg()+warnQueue);
				}
				
				Date date = new Date();
				loggerInfo.info(mqMonitor.getMqConn().getHost()+" : "+ mqMonitor.getQueueNames() + " : "+mqMonitor.getRemark() +" : " + (date.getTime()-curTime));
			}
	} 
	
	public List<MonitorObj> getMonitorList(int warnInterval){
		String sql = "select account,password,host,port,monitor_content,queue_names,compareParam,warn_interval,"
				+ "warn_msg,remark,phones from SUP_MON_MQ where enable=1 and warn_interval=?";
		
		List<MonitorObj> list = getJdbcTemplate().query(sql,new Object[]{warnInterval}, new RowMapper<MonitorObj>(){

			public MonitorObj mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				MqMonitor obj = new MqMonitor();
				MqConn conn = new MqConn();
				
				conn.setAccount(rs.getString("account"));
				conn.setPassword(rs.getString("password"));
				conn.setHost(rs.getString("host"));
				conn.setPort(rs.getInt("port"));
				
				obj.setMqConn(conn);
				obj.setMonitorContent(rs.getString("monitor_content"));
				obj.setMonitorInterval(rs.getInt("warn_interval"));
				obj.setWarnMsg(rs.getString("warn_msg"));
				obj.setRemark(rs.getString("remark"));
				obj.setPhones(rs.getString("phones"));
				obj.setQueueNames(rs.getString("queue_names"));
				obj.setCompareParam(rs.getString("compareParam"));
				return obj;
			}
			
		});
		
		return list;
	}
}
