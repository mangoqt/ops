package com.qt.xxtmonitor.dao;

public interface IMonRestartDao {
	void increaceFault(String id);
	void resumeFault(String id);
	boolean exist(String id);
}
