package com.qt.inspection.model;

public class CheckContentMain {
	//平台的标识，gd-广东 zj-浙江
	private String flag = "gd";
	//巡检的日期，格式：yyyy-MM-dd
	private String checkTime;
	//上午，下午
	private String ampm;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getAmpm() {
		return ampm;
	}
	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
}
