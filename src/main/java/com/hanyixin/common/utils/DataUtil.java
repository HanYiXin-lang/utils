package com.hanyixin.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//日期工具类
/**
 * @ClassName:  DataUtil   
 * @Description:日期工具类    
 * @date:   2020年1月3日 上午9:06:21
 */
public class DataUtil {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * @Title: format   
	 * @Description: 格式化日期   
	 * @param: @param theDate
	 * @param: @param format
	 * @param: @return
	 * @param: @throws ParseException      
	 * @return: String      
	 * @throws
	 */
	public static String format(Date theDate,String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(theDate);
	}
	/**
	 * @Title: parse   
	 * @Description: 解析日期   
	 * @param: @param theDateStr
	 * @param: @param format
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static Date parse(String theDateStr,String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			return simpleDateFormat.parse(theDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Title: getAge   
	 * @Description: 根据指定日期计算年龄  
	 * @param: @param theDate
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int getAge(Date theDate) {
		/** 获取当前日期的年月日 **/
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		/** 获取生日的年月日 **/
		calendar.setTime(theDate);
		int theYear = calendar.get(Calendar.YEAR);
		int theMonth = calendar.get(Calendar.MONTH);
		int theDay = calendar.get(Calendar.DAY_OF_MONTH);
		/** 年龄 **/
		int age = year-theYear;
		/** 判断月份 **/
		if(month<theMonth) {
			age--;
		}
		/** 判断日期 **/
		if(month==theMonth && day<theDay) {
			age--;
		}
		return age;
	}
	/**
	 * @Title: getAge   
	 * @Description: 根据指定日期计算年龄   
	 * @param: @param theDateStr
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int getAge(String theDateStr) {
		Date theDate = parse(theDateStr, "yyyy-MM-dd");
		return getAge(theDate);
	}
	/**
	 * @Title: getDayNum   
	 * @Description: 求两个时间之间的天数
	 * @param: @param date1
	 * @param: @param date2
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int getDayNum(Date date1,Date date2) {
		int dayTime = 1000*60*60*24;
		Long time1 = date1.getTime();
		Long time2 = date2.getTime();
		Long abs = Math.abs(time1-time2);
		Double dayNumDouble = abs/dayTime*1.0;
		return dayNumDouble.intValue();
	}
	/**
	 * @Title: getDayNum   
	 * @Description: 求两个时间之间的天数   
	 * @param: @param date1Str
	 * @param: @param date2Str
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int getDayNum(String date1Str,String date2Str) {
		Date date1 = parse(date1Str, "yyyy-MM-dd");
		Date date2 = parse(date2Str, "yyyy-MM-dd");
		return getDayNum(date1,date2);
	}
	/**
	 * @Title: getDayNum   
	 * @Description: 未来或过去距离现在还有多少天   
	 * @param: @param date1Str
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int getDayNum(String date1Str) {
		Date date1 = new Date();
		Date date2 = parse(date1Str, "yyyy-MM-dd");
		return getDayNum(date1,date2);
	}
	/**
	 * @Title: compare   
	 * @Description: 0-相等
					1- date1大于date2
					-1 date1小于date2   
	 * @param: @param date1
	 * @param: @param date2
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static int compare(Date date1,Date date2) {
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		if(time1==time2) {
			return 0;
		}
		if(time1>time2) {
			return 1;
		}
		return -1;
	}
	/**
	 * @Title: inWeek   
	 * @Description: 判断给定的日期是否在本周之内   
	 * @param: @param theDate
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static boolean inWeek(Date theDate) {
		Calendar calendar = Calendar.getInstance();
		int theDay = calendar.get(Calendar.DAY_OF_WEEK);
		/** 当前周到第一天 **/
		calendar.set(Calendar.DAY_OF_WEEK, 1-theDay);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		/** 当前周到最后一天 **/
		calendar.add(Calendar.DAY_OF_WEEK, 6);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date endDate = calendar.getTime();
		return compare(theDate,startDate)==1 && compare(endDate, theDate)==1;
	}
	/**
	 * @Title: inWeek   
	 * @Description: 判断给定的日期是否在本周之内  
	 * @param: @param theDateStr
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean inWeek(String theDateStr) {
		Date theDate = parse(theDateStr, "yyyy-MM-dd");
		return inWeek(theDate);
	}
	/**
	 * @Title: inMonth   
	 * @Description: 判断指定日期是否在本月   
	 * @param: @param theDate
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean inMonth(Date theDate) {
		Date nowDate = new Date();
		String nowYyyymm = format(nowDate, "yyyy-MM");
		String theYyyymm = format(theDate, "yyyy-MM");
		return nowYyyymm.equals(theYyyymm);
	}
	/**
	 * @Title: inMonth   
	 * @Description: 判断指定日期是否在本月   
	 * @param: @param theDateStr
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean inMonth(String theDateStr) {
		Date theDate = parse(theDateStr, "yyyy-MM-dd");
		return inMonth(theDate);
	}
	
	/**
	 * @Title: getFirstDayOfMonth   
	 * @Description: 获取指定日期月的结束的时间  
	 * @param: @param theDate
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static Date getFirstDayOfMonth(Date theDate) {
		String theDateStr = format(theDate, "yyyy-MM-01");
		return parse(theDateStr, "yyyy-MM-dd");
	}
	/**
	 * @Title: getFirstDayOfMonth   
	 * @Description: 获取指定日期月的第一天    
	 * @param: @param theDateStr
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static Date getFirstDayOfMonth(String theDateStr) {
		Date theDate = parse(theDateStr, "yyyy-MM-dd");
		return getFirstDayOfMonth(theDate);
	}
	/**
	 * @Title: getLastDayOfMonth   
	 * @Description: 获取指定日期月份结束的时间
	 * @param: @param theDate
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static Date getLastDayOfMonth(Date theDate) {
		/** 取当月的第一天 **/
		Date firstDayOfMonth = getFirstDayOfMonth(theDate);
		/** 实例化日历控件 **/
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDayOfMonth);
		/** 下月1号 **/
		calendar.add(Calendar.MONTH, 1);
		/** 减1秒，上月的最后日期 **/
		calendar.add(Calendar.SECOND, -1);
		return calendar.getTime();
	}
	/**
	 * @Title: getLastDayOfMonth   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param theDateStr
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static Date getLastDayOfMonth(String theDateStr) {
		Date theDate = parse(theDateStr, "yyyy-MM-dd HH:mm:ss");
		return getLastDayOfMonth(theDate);
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(format(getLastDayOfMonth("2020-02-06 12:33:33"), "yyyy-MM-dd HH:mm:ss"));
	}

}
