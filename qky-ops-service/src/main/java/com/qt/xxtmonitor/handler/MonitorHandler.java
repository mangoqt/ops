package com.qt.xxtmonitor.handler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.qt.xxtmonitor.model.MonitorObj;

/**
 * @author Tim
 *
 */
public abstract class MonitorHandler {
	@Resource(name="jdbcTemplate")
	public JdbcTemplate jdbcTemplate;
	public abstract void process(List<MonitorObj> MonitorObj);
	
	/**
	 * 告警间隔
	 * @param warnInterval
	 * @return
	 */
	public List<MonitorObj> getMonitorList(int warnInterval){
		return null;
	}
	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
