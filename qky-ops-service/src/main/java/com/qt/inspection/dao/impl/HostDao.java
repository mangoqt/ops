/**
 * <p>User: Tim Tan
 * <p>Date: 2016年5月19日
 * <p>Version: 1.0
 */

package com.qt.inspection.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.qt.core.util.UUIDGenerator;
import com.qt.inspection.dao.IHostDao;
import com.qt.inspection.model.Host;

@Component(value="hostDao")
public class HostDao implements IHostDao{
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Resource(name="jdbcTemplateFalconPortal")
	private JdbcTemplate jdbcTemplateFalconPortal;
	@Resource(name="jdbcTemplateFalconGraph")
	private JdbcTemplate jdbcTemplateFalconGraph;

	@Override
	public List<Host> findAllFalconHosts() {
		List<Host> list = this.jdbcTemplateFalconPortal.query("select hostname, ip from host", 
				new RowMapper<Host>(){

					@Override
					public Host mapRow(ResultSet rs, int rowNum) throws SQLException {
						Host host = new Host();
						host.setHostName(rs.getString("hostname"));
						host.setIp(rs.getString("ip"));
						return host;
					}
			
		});
		return list;
	}
	
	@Override
	public List<Host> findAllHosts() {
		List<Host> list = this.jdbcTemplate.query("select hostname, ip, location,cloud_location from cm_host", 
				new RowMapper<Host>(){

					@Override
					public Host mapRow(ResultSet rs, int rowNum) throws SQLException {
						Host host = new Host();
						host.setHostName(rs.getString("hostname"));
						host.setIp(rs.getString("ip"));
						host.setLocation(rs.getString("location"));
						host.setCloudLocation(rs.getString("cloud_location"));
						return host;
					}
			
		});
		return list;
	}

	@Override
	public void updateHost(Host host) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveHost(Host host) {
		this.jdbcTemplate.update("insert into cm_host(id,hostname,ip,syn_tm) values(?,?,?,?)", 
				new Object[]{UUIDGenerator.getUUID(),host.getHostName(),host.getIp(),new Date()});	
	}

	public Host findHost(String hostName, String ip) {
		List<Host> list = this.jdbcTemplate.query("select id, hostname, ip from cm_host where hostname=? and ip=?",new Object[]{hostName,ip}, 
				new RowMapper<Host>(){

					@Override
					public Host mapRow(ResultSet rs, int rowNum) throws SQLException {
						Host host = new Host();
						host.setId(rs.getString("id"));
						host.setHostName(rs.getString("hostname"));
						host.setIp(rs.getString("ip"));
						return host;
					}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	public void updateHostSynTime(String id) {
		this.jdbcTemplate.update("update cm_host set syn_tm=? where id=?", new Object[]{new Date(), id});		
	}
	
	public void updateHostSynTime(String id, Date date) {
		this.jdbcTemplate.update("update cm_host set syn_tm=? where id=?", new Object[]{date, id});		
	}

	public List<Host> findAllEnpoint() {
		List<Host> list = this.jdbcTemplateFalconGraph.query("select endpoint, t_modify from endpoint", 
				new RowMapper<Host>(){

					@Override
					public Host mapRow(ResultSet rs, int rowNum) throws SQLException {
						Host host = new Host();
						host.setHostName(rs.getString("endpoint"));
						host.setSynTm(rs.getTimestamp("t_modify"));
						return host;
					}
			
		});
		return list;
	}

	public Host findHost(String hostName) {
		List<Host> list = this.jdbcTemplate.query("select id, hostname, ip from cm_host where hostname=?",new Object[]{hostName}, 
				new RowMapper<Host>(){

					@Override
					public Host mapRow(ResultSet rs, int rowNum) throws SQLException {
						Host host = new Host();
						host.setId(rs.getString("id"));
						host.setHostName(rs.getString("hostname"));
						host.setIp(rs.getString("ip"));
						return host;
					}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

}

