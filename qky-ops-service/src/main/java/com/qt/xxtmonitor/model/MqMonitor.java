package com.qt.xxtmonitor.model;

public class MqMonitor extends MonitorObj{
	private MqConn mqConn;
	private String queueNames;
	private String monitorContent;
	private String CompareParam;

	public String getCompareParam() {
		return CompareParam;
	}

	public void setCompareParam(String compareParam) {
		CompareParam = compareParam;
	}

	public String getMonitorContent() {
		return monitorContent;
	}

	public void setMonitorContent(String monitorContent) {
		this.monitorContent = monitorContent;
	}

	public String getQueueNames() {
		return queueNames;
	}

	public void setQueueNames(String queueNames) {
		this.queueNames = queueNames;
	}

	public MqConn getMqConn() {
		return mqConn;
	}

	public void setMqConn(MqConn mqConn) {
		this.mqConn = mqConn;
	}
}
