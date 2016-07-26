/**
 * <p>User: Tim Tan
 * <p>Date: 2016年5月20日
 * <p>Version: 1.0
 */

package com.qt.inspection.model;

public class LoadAvgRsp {
	private String msg;
	private LoadAvg data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public LoadAvg getData() {
		return data;
	}
	public void setData(LoadAvg data) {
		this.data = data;
	}
}

