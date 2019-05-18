package com.zxelec.cpug.vl.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {
	public final static String deyerPattern = "yyyy-MM";
	public final static String defaultPattern = "yyyy-MM-dd";
	public final static String dateTimePattern = "yyyy-MM-dd HH:mm";
	public final static String dateTimeHourPattern = "yyyy-MM-dd HH";
	public final static String dateMonthHourPattern = "M月d日HH:mm";
	public final static String dateMonthPattern = "yyyy年MM月dd日";
	public final static String dateTimeSecondPattern = "yyyy-MM-dd HH:mm:ss";
	public final static String dateTimeSecondPattern_MMSS = "HH:mm:ss";
	public final static String dateTimeSecondPatternSSS = "yyyy-MM-dd HH:mm:ss.SSS";
	public final static String dateTimeYNDPattern = "yyyy年MM月dd日HH点mm分";
	public final static String dateTimeYNDHMSPattern = "yyyyMMddHHmmssSSS";
	public final static String dateYNDPattern = "yyyyMMdd";

	public static String data2String(Date date,String pattern) {
		SimpleDateFormat sdf = getSimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static String data2String() {
		Date date = new Date();
		return data2String(date, dateTimeYNDHMSPattern);
	}
	
	private static SimpleDateFormat getSimpleDateFormat(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf;
	}

}