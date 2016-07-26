package com.qt.xxtmonitor.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qt.core.util.WeixinAPIHelper;

@Controller
@RequestMapping(value="/monitor/wxAlarmSender")
public class WxAlarmSender {
	
	@RequestMapping(value="send",method=RequestMethod.POST)
	public @ResponseBody void send(@RequestParam String content, @RequestParam String tos){
		String [] phoneArray = tos.split(",");
		List<String> list = Arrays.asList(phoneArray);
		WeixinAPIHelper.sendTextMsg(list, content);
	}
}
