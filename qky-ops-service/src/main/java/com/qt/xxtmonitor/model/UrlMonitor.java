package com.qt.xxtmonitor.model;

/**
 * @author Tim
 *
 */
public class UrlMonitor extends MonitorObj{
	//server ip
	private String monitorContent;
	private String url;
	private int connTimeout;
	private String reqMsg;
	private String compareParam;
	//是否检查返回状态，否则检查内容
	private int isCheckStatus;
	//请求类型，GET/POST
	private String reqType;
	private String contentType;
	private String extractValue;
	public String getMonitorContent() {
		return monitorContent;
	}
	public void setMonitorContent(String monitorContent) {
		this.monitorContent = monitorContent;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCompareParam() {
		return compareParam;
	}
	public void setCompareParam(String compareParam) {
		this.compareParam = compareParam;
	}
	public String getReqMsg() {
		return reqMsg;
	}
	public void setReqMsg(String reqMsg) {
		this.reqMsg = reqMsg;
	}
	public int getConnTimeout() {
		return connTimeout;
	}
	public void setConnTimeout(int connTimeout) {
		this.connTimeout = connTimeout;
	}
	public int getIsCheckStatus() {
		return isCheckStatus;
	}
	public void setIsCheckStatus(int isCheckStatus) {
		this.isCheckStatus = isCheckStatus;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getExtractValue() {
		return extractValue;
	}
	public void setExtractValue(String extractValue) {
		this.extractValue = extractValue;
	}

}
