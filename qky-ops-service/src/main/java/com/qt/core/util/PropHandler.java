package com.qt.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropHandler {
	private static Properties p = new Properties();

	static {
		InputStream inputStream;
		inputStream = PropHandler.class.getClassLoader().getResourceAsStream(
				"conf/resources.properties");
		if (inputStream == null) {
			inputStream = PropHandler.class
					.getResourceAsStream("conf/resources.properties");
		}
		

		try {
			p.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String get(String key) {
		return p.getProperty(key);
	}

}
