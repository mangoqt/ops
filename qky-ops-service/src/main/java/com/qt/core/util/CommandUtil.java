package com.qt.core.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * @author Tim
 * 
 */
public class CommandUtil {
	public static String exec(String[] commands) throws IOException, InterruptedException {

		InputStreamReader ir = null;
		LineNumberReader input = null;
		InputStreamReader ire = null;
		LineNumberReader inpute = null;
		String returnStr = "";
		try {
			String str;

			if (commands != null) {
				Process process = Runtime.getRuntime().exec(commands);
				ir = new InputStreamReader(process.getInputStream());
				input = new LineNumberReader(ir);

				while ((str = input.readLine()) != null) {
					returnStr +=  (str+System.getProperty("line.separator"));
				}

				ire = new InputStreamReader(process.getErrorStream());
				inpute = new LineNumberReader(ire);
				while ((str = inpute.readLine()) != null) {
					returnStr +=  (str+System.getProperty("line.separator"));
				}
				process.destroy();
			}

		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (input != null)
					input.close();
				if (ir != null)
					ir.close();
				if (inpute != null)
					inpute.close();
				if (ire != null)
					ire.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return returnStr;
	}
	
	public static void main(String [] args){
		try {
			System.out.println(exec(new String[]{"cmd /c dir"}));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
