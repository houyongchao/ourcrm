package com.kaishengit.util;

import hirondelle.date4j.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

	public static String getTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}
	public static String getYMD(){
		DateTime now = DateTime.now(TimeZone.getDefault());
	    return now.format("YYYY-MM-DD hh:mm:ss");
	}
}
