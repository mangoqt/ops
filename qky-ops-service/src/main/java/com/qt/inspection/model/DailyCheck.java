package com.qt.inspection.model;

import java.util.Date;
import java.util.List;

/**
 * <p>User: Tim Tan
 * <p>Date: 13-2-4 上午9:38
 * <p>Version: 1.0
 */

public class DailyCheck {

	private String id;
	
	private Date createTm;
	
	private Date checkTm;
	
	private String checkUsername;
	
	private boolean checked = Boolean.FALSE;
	
	private String remark;
	
	/**
	 * 巡检信息
	 */
	//集合缓存引起的
	List<CheckInfo> checkInfos;
	
	public DailyCheck(){
		
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<CheckInfo> getCheckInfos() {
		return checkInfos;
	}

	public void setCheckInfos(List<CheckInfo> checkInfos) {
		this.checkInfos = checkInfos;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public Date getCheckTm() {
		return checkTm;
	}

	public void setCheckTm(Date checkTm) {
		this.checkTm = checkTm;
	}

	public String getCheckUsername() {
		return checkUsername;
	}

	public void setCheckUsername(String checkUsername) {
		this.checkUsername = checkUsername;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
