package org.safari.platform.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author Alitalk
 * @version 2013-3-15
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils {
	
	public static final String YEAR = "yyyy";   //年份
	public static final String MM = "MM";       //月份
	public static final String DD = "dd";       //天
	public static final String HH_24 = "HH";    //24小时
	public static final String HH_12 = "hh";    //24小时
	public static final String MI = "mm";       //分
	public static final String SS = "ss";       //秒
	
	public static final String format_year = "yyyy";
	public static final String format_none_month = "yyyyMM";
	public static final String format_line_month = "yyyy-MM";
	public static final String format_slash_month = "yyyy/MM";
	
	public static final String format_none_day = "yyyyMMdd";
	public static final String format_line_day = "yyyy-MM-dd";
	public static final String format_slash_day = "yyyy/MM/dd";
	
	public static final String format_none_hour_24 = "yyyyMMdd HH";
	public static final String format_line_hour_24 = "yyyy-MM-dd HH";
	public static final String format_slash_hour_24 = "yyyy/MM/dd HH";
	
	public static final String format_none_hour_12 = "yyyyMMdd hh";
	public static final String format_line_hour_12 = "yyyy-MM-dd hh";
	public static final String format_slash_hour_12 = "yyyy/MM/dd hh";
	
	public static final String format_none_hour_24_minute = "yyyyMMdd HH:mm";
	public static final String format_line_hour_24_minute = "yyyy-MM-dd HH:mm";
	public static final String format_slash_hour_24_minute = "yyyy/MM/dd HH:mm";
	
	public static final String format_none_hour_12_minute = "yyyyMMdd hh:mm";
	public static final String format_line_hour_12_minute = "yyyy-MM-dd hh:mm";
	public static final String format_slash_hour_12_minute = "yyyy/MM/dd hh:mm";
	
	public static final String format_none_hour_24_minute_second = "yyyyMMdd HH:mm:ss";
	public static final String format_line_hour_24_minute_second = "yyyy-MM-dd HH:mm:ss";
	public static final String format_slash_hour_24_minute_second = "yyyy/MM/dd HH:mm:ss";
	
	public static final String format_none_hour_12_minute_second = "yyyyMMdd hh:mm:ss";
	public static final String format_line_hour_12_minute_second = "yyyy-MM-dd hh:mm:ss";
	public static final String format_slash_hour_12_minute_second = "yyyy/MM/dd hh:mm:ss";
	
	
	public static final String formatUnix_1 = "00%d"; 
	
	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };
	
	/**
	 * 判断日期是否为空
	 * @date 2015年3月10日
	 * @param date Date
	 * @return boolean
	 */
	public static boolean isNull(Date date){
		if(null == date){
			return true;
		}
		return false;
	}
	/**
	 * 将日期字符串转化特定格式的日期
	 * @verson v1.0
	 * @date 2015年3月10日
	 * @param dateStr String 日期格式字符串
	 * @param format String 转化格式
	 * @return Date
	 */
	public static Date strToDate(String dateStr, String format) {
		Date date = null;
		if (null != dateStr && !"".equals(dateStr)) {
			dateStr = dateStr.replaceAll("\\.", "-");
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				date = sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	
	/**
	 * 将日期转化特定格式的日期字符串
	 * @verson v1.0
	 * @date 2015年3月10日
	 * @param date Date 日期
	 * @param format String 转化格式
	 * @return String
	 */
	public static String dateToStr(Date date, String format) {
		String dateStr = "";
		if(!isNull(date)){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			dateStr = sdf.format(date);
		}
		return dateStr;
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
    
	public static Date getDateStart(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd")+" 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getDateEnd(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd") +" 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 判断字符串是否是日期
	 * @param timeString
	 * @return
	 */
	public static boolean isDate(String timeString){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try{
			format.parse(timeString);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * 格式化时间
	 * @param timestamp
	 * @return
	 */
	public static String dateFormat(Date timestamp){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(timestamp);
	}
	
	/**
	 * 获取系统时间Timestamp
	 * @return
	 */
	public static Timestamp getSysTimestamp(){
		return new Timestamp(new Date().getTime());
	}
	
	/**
	 * 获取系统时间Date
	 * @return
	 */
	public static Date getSysDate(){
		return new Date();
	}
	
	/**
	 * 生成时间随机数 
	 * @return
	 */
	public static String getDateRandom(){
		String s=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		return s;
	}
	
	/**
	 * 获取时辰
	 */
	@SuppressWarnings("deprecation")
	public static String welcome(){
		Date now = new Date();
		int hour = now.getHours();
		String greet = "您好！";
		if(hour < 6){
			greet = "凌晨好！";	
		}else if (hour < 9){
			greet = "早上好！";
		}else if (hour < 12){
			greet = "上午好！";
		}else if (hour < 14){
			greet = "中午好！"; 
		}else if (hour < 17){
			greet = "下午好！";
		}else if (hour < 19){
			greet = "傍晚好！";
		}else if (hour < 22){
			greet = "晚上好！";
		}else {
			greet = "夜里好！";
		} 
		return greet;
	}
	
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
		System.out.println(welcome());
	}
}
