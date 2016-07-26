package com.qt.xxtmonitor.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.exceptions.JedisConnectionException;

import com.qt.core.util.CompareUtil;
import com.qt.xxtmonitor.model.MonitorObj;
import com.qt.xxtmonitor.model.RedisMonitor;

/**
 * @author Tim
 * 
 */
@Component(value = "redisMonitorHandler")
public class RedisMonitorHandler extends MonitorHandler {

	private static final Log loggerInfo = LogFactory.getLog("INFO."
			+ RedisMonitorHandler.class.getName());
	private static final Log logger = LogFactory.getLog(RedisMonitorHandler.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qt.xxtmonitor.handler.MonitorHandler#process(java.util.List)
	 */
	@Override
	public void process(final List<MonitorObj> list) throws MonitorException {

		
		for (MonitorObj obj : list) {
			RedisMonitor redisMonitor = (RedisMonitor) obj;
			Jedis jedis = null;

			long curTime = System.currentTimeMillis();
			try {
				
				JedisShardInfo info = new JedisShardInfo(redisMonitor.getHost(),redisMonitor.getPort());
				info.setTimeout(redisMonitor.getConnTimeout());
				jedis = new Jedis(info);
				String value = jedis.get(redisMonitor.getMonitorKey());
				if(value == null){
					value = "";
				}
				boolean flag = CompareUtil.compare(value,
						redisMonitor.getMonitorContent(),
						redisMonitor.getCompareParam());

				if(flag){
					redisMonitor.setError(false);
				}else{
					redisMonitor.setWarnMsg(redisMonitor.getWarnMsg()+":"+value);
				}
				
				Date date = new Date();
				loggerInfo.info(redisMonitor.getHost()+":"+ redisMonitor.getPort() + " : " + value
						+ " : " + redisMonitor.getRemark() + " : "
						+ (date.getTime() - curTime));

			}catch(JedisConnectionException e){
				redisMonitor.setWarnMsg(redisMonitor.getWarnMsg()+":"+e.getMessage());
				redisMonitor.setError(true);
				logger.error(null,e);
			} catch (Exception e) {
				logger.error(null, e);
				redisMonitor.setError(true);
				redisMonitor.setWarnMsg(redisMonitor.getWarnMsg()+":"+e.getMessage());
			}
		}
	}

	public List<MonitorObj> getMonitorList(int warnInterval) {
		String sql = "select type,monitor_content,warn_interval,monitor_key,host,port,pwd,conn_time_out,"
				+ "warn_msg,remark,phones,compareParam from SUP_MON_REDIS where enable=1 and warn_interval=?";
		
		List<MonitorObj> list = getJdbcTemplate().query(sql,new Object[]{warnInterval}, new RowMapper<MonitorObj>(){

			public MonitorObj mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				RedisMonitor obj = new RedisMonitor();
				obj.setMonitorContent(rs.getString("monitor_content"));
				obj.setMonitorKey(rs.getString("monitor_key"));
				obj.setHost(rs.getString("host"));
				obj.setPort(rs.getInt("port"));
				obj.setPwd(rs.getString("pwd"));
				obj.setConnTimeout(rs.getInt("conn_time_out"));
				obj.setMonitorInterval(rs.getInt("warn_interval"));
				obj.setWarnMsg(rs.getString("warn_msg"));
				obj.setRemark(rs.getString("remark"));
				obj.setPhones(rs.getString("phones"));
				obj.setCompareParam(rs.getString("compareParam"));
				return obj;
			}
			
		});
		
		return list;
	}
}
