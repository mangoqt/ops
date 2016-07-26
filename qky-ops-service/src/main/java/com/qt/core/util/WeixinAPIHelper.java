package com.qt.core.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.qt.wx.msg.rsp.Token;

public class WeixinAPIHelper {

	public static void sendTextMsg(List<String> userList,String content) {
		Token token = (Token) JsonUtil.getInstance().json2obj(getToken(), Token.class);
		String tokenStr = token.getAccess_token();

		StringBuffer sb = new StringBuffer();
		for(String str : userList){
			sb.append(str).append("|");
		}
		String username = sb.delete(sb.length()-1, sb.length()).toString();
		
		String strJson = "{\"touser\" :\""+username+"\",";
		strJson += "\"msgtype\":\"text\",";
		strJson += "\"agentid\": 1 ,";
		strJson += "\"text\":{";
		strJson += "\"content\":\""+content+"\"";
		strJson += "}}";
		
		String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + tokenStr;

		try {
			String str = HttpClientUtil.post(url, strJson, null);
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

	public static String getToken(){
		String corpId="wxb173d3e80810e182";
		String secret="5s7o3z1NXGM8wjDrIQLy34I3D_hGJH8MAY0Ocp82MhXMID4aH3LDbApOuJWmdSfm";
		String tokenUrl="https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpId+"&corpsecret="+secret;
		String str = "";
		try {
			str = HttpClientUtil.post(tokenUrl, "", null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str;
		
	}
	
	public static void main(String args []){
		
		String[] userArray = new String[]{"tantingzhao"};
		WeixinAPIHelper.sendTextMsg(Arrays.asList(userArray), "test1234");
	}
}