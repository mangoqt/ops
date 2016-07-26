package com.qt.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * User: Tim Tan
 * <p>
 * Date: 2016年6月2日
 * <p>
 * Version: 1.0
 */

public class IpUtil {
	/**
	 * 判断是否为合法IP
	 * 
	 * @return the ip
	 */
	public static boolean isboolIp(String ipAddress) {
		String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}
}
