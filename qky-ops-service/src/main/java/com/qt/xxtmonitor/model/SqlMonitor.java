package com.qt.xxtmonitor.model;

/**
 * @author Tim
 * 
 */
public class SqlMonitor extends MonitorObj {
	// server ip
	private String monitorContent;
	private String sql;
	private String compareParam;
	
	public String getMonitorContent() {
		return monitorContent;
	}
	public void setMonitorContent(String monitorContent) {
		this.monitorContent = monitorContent;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getCompareParam() {
		return compareParam;
	}
	public void setCompareParam(String compareParam) {
		this.compareParam = compareParam;
	}

}
