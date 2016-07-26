package com.qt.xxtmonitor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qt.inspection.CheckService;

/**
 * @author Tim
 *
 */
public class XxtMonitorTest2 {
	
	public static void main(String args[]){
		new XxtMonitorTest2().test();
	}
	
	public void test(){
		ApplicationContext context = new ClassPathXmlApplicationContext("bean_test.xml");
		CheckService service = (CheckService) context.getBean("checkService");
		service.dailyCheck();
	}
}
