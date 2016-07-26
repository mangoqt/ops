package com.qt.inspection.model;

/**
 * <p>User: Tim Tan
 * <p>Date: 2016年6月21日
 * <p>Version: 1.0
 */

public class DfstatRsp {
	private String msg;
	private String[][] data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String[][] getData() {
		return data;
	}
	public void setData(String[][] data) {
		this.data = data;
	}
}

