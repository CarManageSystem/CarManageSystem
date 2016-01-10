package com.manage.tools;

import java.text.SimpleDateFormat;


public class XDateTime {

	public static String stringValueWithCurrent() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		String dateString = dateFormat.format(new java.util.Date());
		return dateString;
	}
	
	public static java.util.Date getDate() {
		return new java.util.Date();
	}
	
}
