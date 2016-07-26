/**
 * <p>User: Tim Tan
 * <p>Date: 2016年5月20日
 * <p>Version: 1.0
 */

package com.qt.inspection.dao;

import com.qt.inspection.model.CheckInfo;
import com.qt.inspection.model.DailyCheck;

public interface IDailyCheckDao {
	void save(CheckInfo info);
	
	void save(DailyCheck check);
}

