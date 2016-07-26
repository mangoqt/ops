package com.qt.xxtmonitor.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.qt.core.util.CompareUtil;
import com.qt.core.util.PropHandler;
import com.qt.xxtmonitor.model.MonitorObj;
import com.qt.xxtmonitor.model.SqlMonitor;

/**
 * @author Tim
 * 
 */
@Component(value = "sqlMonitorHandler")
public class SqlMonitorHandler extends MonitorHandler {

	private static final Log loggerInfo = LogFactory.getLog("INFO."
			+ SqlMonitorHandler.class.getName());
	private static final Log logger = LogFactory.getLog(SqlMonitorHandler.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qt.xxtmonitor.handler.MonitorHandler#process(java.util.List)
	 */
	@Override
	public void process(final List<MonitorObj> list) throws MonitorException {

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(PropHandler.get("connection.driverClassName"));
		ds.setUrl(PropHandler.get("connection.url"));
		ds.setUsername(PropHandler.get("connection.username"));
		ds.setPassword(PropHandler.get("connection.password"));
		JdbcTemplate oraJdbcTempleate = new JdbcTemplate(ds);
		
		for (MonitorObj obj : list) {
			SqlMonitor sqlMonitor = (SqlMonitor) obj;

			String sql = sqlMonitor.getSql();

			long curTime = System.currentTimeMillis();
			int count = 0;
			try {
				if(PropHandler.get("sqlmonitor_timeout") == null){
					oraJdbcTempleate.setQueryTimeout(5);
				}else{
					oraJdbcTempleate.setQueryTimeout(Integer.valueOf(PropHandler.get("sqlmonitor_timeout")));
				}
				
				count = oraJdbcTempleate.queryForObject(sql, Integer.class);
				boolean flag = CompareUtil.compare(count,
						sqlMonitor.getMonitorContent(),
						sqlMonitor.getCompareParam());

				if(flag){
					sqlMonitor.setError(false);
				}else{
					sqlMonitor.setWarnMsg(sqlMonitor.getWarnMsg()+":"+count);
				}
				
				Date date = new Date();
				loggerInfo.info(sqlMonitor.getSql() + " : " + count
						+ " : " + sqlMonitor.getRemark() + " : "
						+ (date.getTime() - curTime));

			}catch(UncategorizedSQLException e1){
				
				if(e1.getMessage().contains("ORA-01013")){
					sqlMonitor.setWarnMsg(sqlMonitor.getWarnMsg()+"执行超时");
				}
				logger.error(null,e1);
			} catch (DataAccessException e) {
				logger.error(null, e);
				sqlMonitor.setError(true);
				sqlMonitor.setWarnMsg(sqlMonitor.getWarnMsg()+"error");
			}
		}
	}

	public List<MonitorObj> getMonitorList(int warnInterval) {
		String sql = "select type,monitor_content,warn_interval,"
				+ "warn_msg,remark,phones,sql1,compareParam from SUP_MON_SQL where enable=1 and warn_interval=?";
		
		List<MonitorObj> list = getJdbcTemplate().query(sql, new Object[]{warnInterval},new RowMapper<MonitorObj>(){

			public MonitorObj mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				SqlMonitor obj = new SqlMonitor();
				obj.setMonitorContent(rs.getString("monitor_content"));
				obj.setMonitorInterval(rs.getInt("warn_interval"));
				obj.setWarnMsg(rs.getString("warn_msg"));
				obj.setRemark(rs.getString("remark"));
				obj.setPhones(rs.getString("phones"));
				obj.setCompareParam(rs.getString("compareParam"));
				obj.setSql(rs.getString("sql1"));
				return obj;
			}
			
		});
		
		return list;
	}
}
