package com.qt.xxtmonitor.model;

public class RedisMonitor extends MonitorObj{
	private String host;
	private int port;
	private String pwd;
	private int connTimeout;
	private String monitorContent;
	private String monitorKey;
	private String compareParam;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getConnTimeout() {
		return connTimeout;
	}
	public void setConnTimeout(int connTimeout) {
		this.connTimeout = connTimeout;
	}
	public String getMonitorContent() {
		return monitorContent;
	}
	public void setMonitorContent(String monitorContent) {
		this.monitorContent = monitorContent;
	}
	public String getCompareParam() {
		return compareParam;
	}
	public void setCompareParam(String compareParam) {
		this.compareParam = compareParam;
	}
	public String getMonitorKey() {
		return monitorKey;
	}
	public void setMonitorKey(String monitorKey) {
		this.monitorKey = monitorKey;
	}
}
