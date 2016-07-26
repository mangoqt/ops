package com.qt.inspection.model;

public class CheckInfo{
	
	private String id;
	private String hostName;
	private String ip;
	private DailyCheck dailyCheck;
	private String load5Min;
	private String cpuUsage;
	private String memFree;
	private String memUsageRate;
	private String dfUsage;
	private Double dfMaxUsage;
	private String uptime;
	private String serverTime;
	private String dailyCheckId;
	

	public DailyCheck getDailyCheck() {
		return dailyCheck;
	}

	public void setDailyCheck(DailyCheck dailyCheck) {
		this.dailyCheck = dailyCheck;
	}

	public String getLoad5Min() {
		return load5Min;
	}

	public void setLoad5Min(String load5Min) {
		this.load5Min = load5Min;
	}

	public String getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(String cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public String getMemFree() {
		return memFree;
	}

	public void setMemFree(String memFree) {
		this.memFree = memFree;
	}

	public String getDfUsage() {
		return dfUsage;
	}

	public void setDfUsage(String dfUsage) {
		this.dfUsage = dfUsage;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getServerTime() {
		return serverTime;
	}

	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}

	public String getDailyCheckId() {
		return dailyCheckId;
	}

	public void setDailyCheckId(String dailyCheckId) {
		this.dailyCheckId = dailyCheckId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemUsageRate() {
		return memUsageRate;
	}

	public void setMemUsageRate(String memUsageRate) {
		this.memUsageRate = memUsageRate;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Double getDfMaxUsage() {
		return dfMaxUsage;
	}

	public void setDfMaxUsage(Double dfMaxUsage) {
		this.dfMaxUsage = dfMaxUsage;
	}
}
