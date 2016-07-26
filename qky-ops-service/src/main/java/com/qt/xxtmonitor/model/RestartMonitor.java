package com.qt.xxtmonitor.model;

import java.util.Date;

public class RestartMonitor extends MonitorObj{
	private String appName;
	private Integer isAutoRestart;
	private Integer isFault;
	private String command;
	private Date lastRestartDate;
	private Integer faultCountToRestart;
	private String serverId;
	private ServerLoginInfo serverLoginInfo;
	
	public ServerLoginInfo getServerLoginInfo() {
		return serverLoginInfo;
	}
	public void setServerLoginInfo(ServerLoginInfo serverLoginInfo) {
		this.serverLoginInfo = serverLoginInfo;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public Integer getFaultCountToRestart() {
		return faultCountToRestart;
	}
	public void setFaultCountToRestart(Integer faultCountToRestart) {
		this.faultCountToRestart = faultCountToRestart;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Integer getIsAutoRestart() {
		return isAutoRestart;
	}
	public void setIsAutoRestart(Integer isAutoRestart) {
		this.isAutoRestart = isAutoRestart;
	}
	public Integer getIsFault() {
		return isFault;
	}
	public void setIsFault(Integer isFault) {
		this.isFault = isFault;
	}
	public Date getLastRestartDate() {
		return lastRestartDate;
	}
	public void setLastRestartDate(Date lastRestartDate) {
		this.lastRestartDate = lastRestartDate;
	}
}
