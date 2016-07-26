package com.qt.xxtmonitor.model;

/**
 * @author Tim
 *	监控对象
 */
public class MonitorObj {
	//唯一ID
	private String id;
	//监控信息
	private String warnMsg;
	//监控间隔(秒）
	private int monitorInterval;
	//备注
	private String remark;
	//是否出错
	private boolean isError=true;
	//电话,逗号隔开
	private String phones; 
	//错误代码
	private String errorCode;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the warnMsg
	 */
	public String getWarnMsg() {
		return warnMsg;
	}
	/**
	 * @param warnMsg the warnMsg to set
	 */
	public void setWarnMsg(String warnMsg) {
		this.warnMsg = warnMsg;
	}
	/**
	 * @return the monitorInterval
	 */
	public int getMonitorInterval() {
		return monitorInterval;
	}
	/**
	 * @param monitorInterval the monitorInterval to set
	 */
	public void setMonitorInterval(int monitorInterval) {
		this.monitorInterval = monitorInterval;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the isError
	 */
	public boolean isError() {
		return isError;
	}
	/**
	 * @param isError the isError to set
	 */
	public void setError(boolean isError) {
		this.isError = isError;
	}
	/**
	 * @return the phones
	 */
	public String getPhones() {
		return phones;
	}
	/**
	 * @param phones the phones to set
	 */
	public void setPhones(String phones) {
		this.phones = phones;
	}
}
