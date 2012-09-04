package com.kaishengit.util;

public class StringUtil {

	public static boolean isEmpty(String str){
		if (str == null) {
			return true;
		} else if("".equals(str)){
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
}
