package com.kaishengit.util;
/**
 * ��õ�ǰ��ҳ��
 *
 */
public class PagerNum {
	public static int getPresentNum(String p){
		int pagerNum = 1;//ҳ��
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
