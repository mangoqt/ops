package com.qt.mail;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qt.core.mail.MailBean;
import com.qt.core.mail.MailUtil;
import com.qt.core.util.FreemarkerUtil;

@ContextConfiguration("classpath:conf/bean-mail.xml")
public class MailServiceTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private MailUtil mailUtil;

	public void testSendMail() {

		// 创建邮件
		MailBean mailBean = new MailBean();
		mailBean.setFrom("ydxxtmonitor@126.com");
		mailBean.setSubject("hello world");
		mailBean.setToEmails(new String[] { "13512719456@139.com" });
		mailBean.setTemplate("hello ${userName} !<a href='www.baidu.com' >baidu</a>");
		Map map = new HashMap();
		map.put("userName", "Tim Tan");
		mailBean.setData(map);

		// 发送邮件
		try {
			mailUtil.send(mailBean);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFreemaker(){
		FreemarkerUtil util = FreemarkerUtil.getInstance("/ftl");
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("name", "Tim Tan");
		StringWriter sw = new StringWriter();
		util.sprint(root, "monitor_mail.ftl",sw);
		System.out.println(sw.getBuffer().toString());
	}
}
