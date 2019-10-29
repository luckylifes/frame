package com.dj.cn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String dateToStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	public static String dateToStr(Date date, String partty) {
		SimpleDateFormat format = new SimpleDateFormat(partty);
		return format.format(date);
	}
	
	
	public static Date strToDate(String date) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.parse(date);
	}
	
	public static Date strToDate(String date, String partty) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(partty);
		return format.parse(date);
	}
}
