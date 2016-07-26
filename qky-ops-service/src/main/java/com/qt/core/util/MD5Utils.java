package com.qt.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * MD5工具类
 *
 * @author linjingjie
 * @create 2016-03-16 17:14
 */
public class MD5Utils {

	public static final String UTF8 = "UTF-8";
	public static final String MD5 = "MD5";

	/***
	 * 返回MD5加密字符串
	 * 
	 * @param input
	 * @return
	 */
	public static String md5Encode(String input) {
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		return md5PasswordEncoder.encodePassword(input, null);
		// MessageDigest messageDigest = MessageDigest.getInstance(MD5);
		// messageDigest.update(input.getBytes(UTF8));
		// return new BigInteger(1, messageDigest.digest()).toString(16);
	}
	
	/**
	 * 判断是否为合法IP
	 * 
	 * @return the ip
	 */
	public static boolean isAccessToken(String accessToken) {
//		String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		String reg = "";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(accessToken);
		return matcher.matches();
	}
		
}