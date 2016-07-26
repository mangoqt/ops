package com.qt.xxtmonitor.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.qt.xxtmonitor.dao.IMonRestartDao;

@Component(value="monRestartDao")
public class MonRestartDao implements IMonRestartDao{

	@Resource(name="jdbcTemplate")
	public JdbcTemplate jdbcTemplate;
	
	public void increaceFault(String id) {
		String sql = "update SUP_MON_RESTART set is_fault=is_fault+1 where app_name=?";
		this.jdbcTemplate.update(sql,new Object[]{id});
		
	}

	public void resumeFault(String id) {
		String sql = "update SUP_MON_RESTART set is_fault=0 where app_name=?";
		this.jdbcTemplate.update(sql,new Object[]{id});
	}
	
	public boolean exist(String id){
		String sql = "select count(1) from SUP_MON_RESTART where app_name=?";
		int count = this.jdbcTemplate.queryForObject(sql, new Object[]{id} , Integer.class);
		if(count > 0 ){
			return true;
		}
		
		return false;
	}

}
