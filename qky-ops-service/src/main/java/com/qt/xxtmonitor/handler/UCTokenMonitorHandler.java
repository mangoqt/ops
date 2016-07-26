package com.qt.xxtmonitor.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.qt.core.util.HttpClientUtil;
import com.qt.core.util.MD5Utils;
import com.qt.xxtmonitor.model.MonitorObj;

/**
 * <p>User: Tim Tan
 * <p>Date: 2016年6月24日
 * <p>Version: 1.0
 */

public class UCTokenMonitorHandler  extends MonitorHandler{

	@Override
	public void process(List<MonitorObj> MonitorObj) {

		String userName = "13512719456";
		String encryptPwd = MD5Utils.md5Encode("qky123456");

		/*
		 * String url =
		 * "http://uww-dev.thinkjoy.com.cn/v2/oauth/token?client_id=demo"+
		 * "&client_secret=demo&grant_type=password"+ "&scope=read&username=" +
		 * userName + "&password=" + encryptPwd;
		 */
		String url = "http://uww-dev.thinkjoy.com.cn/v2/oauth/token";

		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("client_id", "demo"));
		list.add(new BasicNameValuePair("client_secret", "demo"));
		list.add(new BasicNameValuePair("grant_type", "password"));
		list.add(new BasicNameValuePair("scope", "read"));
		list.add(new BasicNameValuePair("username", userName));
		list.add(new BasicNameValuePair("password", encryptPwd));

		System.out.println(HttpClientUtil.get(url, list));

		String access_token = JSON.parseObject(HttpClientUtil.get(url, list)).getJSONObject("bizData")
				.getString("value");
		System.out.println(access_token);//len=36
	}

}

