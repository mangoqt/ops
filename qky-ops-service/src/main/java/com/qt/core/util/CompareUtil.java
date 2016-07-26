package com.qt.core.util;

public class CompareUtil {
	private static String LT = "<";
	private static String LE = "<=";
	private static String GT = ">";
	private static String GE = ">=";
	private static String EQ = "=";
	private static String CONTAINS = "contains";
	
	
	/**
	 * 内容与结果比较
	 * @param result1
	 * @param content1
	 * @param param1
	 * @return
	 */
	public static boolean compare(Object result1, String content1, String param1){
		boolean flag= false;
		
		String result;
		if(result1 instanceof Integer){
			result = Integer.toString((Integer) result1);
		}else{
			result = new String((String) result1).trim();
		}
		String content = content1.trim();
		String param = param1.trim();
		
		try {
			if(LT.equals(param)){
				flag = (Integer.valueOf(result)<Integer.valueOf(content));
			}else if(GT.equals(param)){
				flag = (Integer.valueOf(result)>Integer.valueOf(content));
			}else if(LE.equals(param)){
				flag = (Integer.valueOf(result)<=Integer.valueOf(content));
			}else if(GE.equals(param)){
				flag = (Integer.valueOf(result)>=Integer.valueOf(content));
			}else if(EQ.equals(param)){
				if(result1 instanceof Integer ){
					flag = (Integer.valueOf(result)==Integer.valueOf(content));
				}else{
					flag = (result.equals(content));
				}
				
			}else if(CONTAINS.equals(result)){
				flag = result.contains(content);
			}
		} catch (NumberFormatException e) {
			flag = false;
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public static void main(String[] args){
		System.out.println(CompareUtil.compare("1 ", "1 ", "="));
	}
	
}
