/**
 * <p>User: Tim Tan
 * <p>Date: 2016年5月19日
 * <p>Version: 1.0
 */

package com.qt.inspection.model;

import java.util.Date;

public class Host {
	private String id;
	private String hostName;
	private String ip;
	private Date synTm;
	private Date createTm;
	private String location;
	private String cloudLocation;
	
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
	public Date getSynTm() {
		return synTm;
	}
	public void setSynTm(Date synTm) {
		this.synTm = synTm;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTm() {
		return createTm;
	}
	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}
	public String getCloudLocation() {
		return cloudLocation;
	}
	public void setCloudLocation(String cloudLocation) {
		this.cloudLocation = cloudLocation;
	}
}

