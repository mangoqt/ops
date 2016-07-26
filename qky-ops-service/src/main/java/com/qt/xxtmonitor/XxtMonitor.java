package com.qt.xxtmonitor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Tim
 *
 */
public class XxtMonitor {
	
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext("conf/bean.xml");
//		MonitorService service = (MonitorService) context.getBean("monitorService");
//		service.startMonitor();
	}
}
