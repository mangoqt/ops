/**
 * <p>User: Tim Tan
 * <p>Date: 2016年5月19日
 * <p>Version: 1.0
 */

package com.qt.inspection.dao;

import java.util.List;

import com.qt.inspection.model.Host;

public interface IHostDao {
	List<Host> findAllFalconHosts();
	
	List<Host> findAllHosts();
	
	void updateHost(Host host);
	
	void saveHost(Host host);
	
	public Host findHost(String hostName, String ip);

	public void updateHostSynTime(String id) ;
}

