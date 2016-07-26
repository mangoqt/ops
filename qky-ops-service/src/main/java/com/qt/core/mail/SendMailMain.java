package com.qt.core.mail;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qt.core.util.PropHandler;

/**
 * @author: Tim
 * @since: Jan 29, 2015
 */
public class SendMailMain {

	private static final Log logger = LogFactory.getLog(SendMailMain.class);
	

	public static void main(String[] args) {
		if (args != null && args.length == 1) {
			
			SendMailMain main = new SendMailMain();
			main.send(args);
		} else {
			logger.info("邮件发送失败！");
		}
	}
	
	public void send(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("conf/bean-mail.xml");
		String[] mailArray = PropHandler.get("mail.tolist").split(",");
		// 创建邮件
		MailBean mailBean = new MailBean();
		mailBean.setFrom(PropHandler.get("mail.from"));
		mailBean.setSubject("monitor");
		mailBean.setToEmails(mailArray);
		mailBean.setTemplate(args[0]);
		try {
			((MailUtil) context.getBean("mailUtil")).send(mailBean);
		} catch (MessagingException e) {
			logger.error(null,e);
		}
		
		context.close();
	}

}
