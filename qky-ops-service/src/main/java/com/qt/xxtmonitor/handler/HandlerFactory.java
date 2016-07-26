package com.qt.xxtmonitor.handler;

import java.util.LinkedList;
import java.util.List;

/**
 * 监控处理器工厂
 * 
 * @author Tim
 *
 */
public class HandlerFactory {
	private static List<MonitorHandler> handlers = new LinkedList<MonitorHandler>();
	
	private static final String LOG_MONITOR_COMMAND = "LOG_MONITOR_COMMAND";
	
	static{
		handlers.add(new ScriptMonitorHandler());
	}
	
	public static List<MonitorHandler> getHandlers(){
		return handlers;
	}
} 
