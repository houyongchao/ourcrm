package com.kaishengit.util;
/**
 * 获得当前的页数
 *
 */
public class PagerNum {
	public static int getPresentNum(String p){
		int pagerNum = 1;//页数
		if(StringUtil.isNotEmpty(p)){
			try {
				pagerNum = Integer.parseInt(p);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return pagerNum;
	}

}
