package com.qt.xxtmonitor.model;

public class ServerLoginInfo {
	private String id;
	private String ip;
	private Integer port;
	private Integer isAuthByKey;
	private String loginUser;
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Integer getIsAuthByKey() {
		return isAuthByKey;
	}
	public void setIsAuthByKey(Integer isAuthByKey) {
		this.isAuthByKey = isAuthByKey;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
