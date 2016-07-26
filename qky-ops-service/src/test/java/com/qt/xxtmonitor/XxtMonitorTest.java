package com.qt.xxtmonitor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Tim
 *
 */
public class XxtMonitorTest {
	
	public static void main(String args[]){
		new XxtMonitorTest().test();
	}
	
	public void test(){
		ApplicationContext context = new ClassPathXmlApplicationContext("bean_test.xml");
		MonitorService service = (MonitorService) context.getBean("monitorService");
		service.startMonitor(5);
	}
}
