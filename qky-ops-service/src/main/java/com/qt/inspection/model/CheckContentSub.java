package com.qt.inspection.model;

public class CheckContentSub {

	/*
	 'serverip':'服务器的IP', 
	 'cpu_usage':'CPU使用率', 
	 'load':'负载', 
	 'disk_usage':'磁盘', 
	 'swap':'swap占用情况', 
	 'c_tomcat':'tomcat_web_virt', 
	 'c_weblogic':'weblogic_web_virt',
	 'c_jboss':'jboss_web_virt',
	 'process_num':'线程数'*/
	private String serverip;
	private String cpuUsage;
	private String load;
	private String diskUsage;
	private String swap;
//	private String c_tomcat;
//	private String c_weblogic_web_virt;
//	private String c_jboss;
	private String processNum;
	private String memory;
	private String memoryUsage;
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getMemoryUsage() {
		return memoryUsage;
	}
	public void setMemoryUsage(String memoryUsage) {
		this.memoryUsage = memoryUsage;
	}
	public String getServerip() {
		return serverip;
	}
	public void setServerip(String serverip) {
		this.serverip = serverip;
	}
	public String getCpuUsage() {
		return cpuUsage;
	}
	public void setCpuUsage(String cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	public String getLoad() {
		return load;
	}
	public void setLoad(String load) {
		this.load = load;
	}
	public String getDiskUsage() {
		return diskUsage;
	}
	public void setDiskUsage(String diskUsage) {
		this.diskUsage = diskUsage;
	}
	public String getSwap() {
		return swap;
	}
	public void setSwap(String swap) {
		this.swap = swap;
	}
	public String getProcessNum() {
		return processNum;
	}
	public void setProcessNum(String processNum) {
		this.processNum = processNum;
	}
	
}
