package com.qt.xxtmonitor.model;

/**
 * @author Tim
 *
 */
public class ScriptMonitor extends MonitorObj{
	//server ip
	private String serverIp;
	private String logFullName;
	private String monitorContent;
	private String script;
	private String compareParam;
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public String getCompareParam() {
		return compareParam;
	}
	public void setCompareParam(String compareParam) {
		this.compareParam = compareParam;
	}
	/**
	 * @return the serverIp
	 */
	public String getServerIp() {
		return serverIp;
	}
	/**
	 * @param serverIp the serverIp to set
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	/**
	 * @return the logFullName
	 */
	public String getLogFullName() {
		return logFullName;
	}
	/**
	 * @param logFullName the logFullName to set
	 */
	public void setLogFullName(String logFullName) {
		this.logFullName = logFullName;
	}
	/**
	 * @return the monitorContent
	 */
	public String getMonitorContent() {
		return monitorContent;
	}
	/**
	 * @param monitorContent the monitorContent to set
	 */
	public void setMonitorContent(String monitorContent) {
		this.monitorContent = monitorContent;
	}
}
