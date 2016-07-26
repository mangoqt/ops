package com.qt.xxtmonitor.model;

/**
 * @author: Tim
 * @since: Mar 27, 2015
 */
public class DbSqlMonitor extends SqlMonitor{
	private String driverClassName = "";
	private String dbUsername = "";
	private String dbUrl = "";
	private String dbPassword = "";
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getDbUsername() {
		return dbUsername;
	}
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
}
