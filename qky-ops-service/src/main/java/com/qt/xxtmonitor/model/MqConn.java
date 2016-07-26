package com.qt.xxtmonitor.model;

public class MqConn {
	private String host;
	private int port;
	private String account;
	private String password;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	public boolean equal(MqConn mqConn){
		if(mqConn.getHost().equals(this.host) && mqConn.getPort().equals(this.port)){
			return true;
		}else{
			return false;
		}
	}*/
}
