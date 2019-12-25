package cn.tedu.home.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static Date parseTime(String time, String pattern) {
		try {
			Date date =  new SimpleDateFormat(pattern).parse(time);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
