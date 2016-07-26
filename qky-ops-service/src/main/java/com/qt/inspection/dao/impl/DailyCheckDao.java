/**
 * <p>User: Tim Tan
 * <p>Date: 2016年5月20日
 * <p>Version: 1.0
 */

package com.qt.inspection.dao.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.qt.inspection.dao.IDailyCheckDao;
import com.qt.inspection.model.CheckInfo;
import com.qt.inspection.model.DailyCheck;

@Component(value="dailyCheckDao")
public class DailyCheckDao implements IDailyCheckDao{

	@Resource(name="jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(CheckInfo info) {
		this.jdbcTemplate.update("insert into cm_check_info(id,hostname,ip,daily_check_id,load_5min,cpu_usage,mem_free,mem_usage_rate,df_usage,df_max_usage,uptime,server_tm) values(?,?,?,?,?,?,?,?,?,?,?,?)",
				new Object[]{info.getId(),info.getHostName(),info.getIp(),info.getDailyCheckId(),info.getLoad5Min(),info.getCpuUsage(),info.getMemFree(),info.getMemUsageRate(),info.getDfUsage(),info.getDfMaxUsage(),info.getUptime(),info.getServerTime()});
	}

	@Override
	public void save(DailyCheck check) {
		this.jdbcTemplate.update("insert into cm_daily_check(id,create_tm) values(?,?)",
				new Object[]{check.getId(),new Date()});
	}

}

