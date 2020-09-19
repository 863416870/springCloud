package cc.young.common.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

    public final static  Integer HOUR_TIME = 1;
    public final static  Integer DAY_TIME = 24;
    public final static  Long DAY_SECONDS = 86400L;


    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyyMMdd） */
    public static final String DATE_NOFUll_FORMAT = "yyyyMMdd";
    /** 时间格式(yyyyMMdd） */
    public static final String DATE_PATTERN_FORMAT_MONTH = "yyyyMM";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public final static String DATE_TIME_PATTERN_MINUTE = "yyyy-MM-dd HH:mm";

    /**
     *  获得当前时间戳
     */
    public static Integer getCurrentTime() {
        return (int) (DateUtil.currentSeconds());
    }

    /**
     * 获得当前年
     */
    public static Integer getCurrentYear(){
        return DateUtil.thisYear();
    }

    /**
     * @param dateStr 时间字符串 2019-08-09 请勿写成2019-8-9
     * @return  2019-08-09对应的时间戳
     */
    public static Integer convertStr2Time(String dateStr) {
        return (int) ((DateUtil.parse(dateStr)).getTime()/1000);
    }

    /**
     * @param dateStr 时间字符串 2019-08-09
     * @param pattern 匹配模式
     * @return
     */
    public static Integer convertStr2Time(String dateStr, String pattern)
    {
        DateTime dateTime = DateUtil.parse(dateStr);
        String format = DateUtil.format(dateTime,pattern);
        DateTime date = DateUtil.parse(format,pattern);
        return (int) (date.getTime()/1000) ;
    }


    /**
     * @param timestamp 时间戳
     * @return  "2019-08-20 15:31:49"
     */
    public static String convertTime2Str(Integer timestamp) {
        return convertTime2Str(timestamp,DATE_TIME_PATTERN);
    }

    /**
     * @param timestamp 时间戳
     * @param pattern   匹配模式
     * @return
     */
    public static String convertTime2Str(Integer timestamp, String pattern) {
        DateTime date = DateUtil.date(timestamp * 1000L);
        return DateUtil.format(date, pattern);
    }
    /**
     * @param timestamp 时间戳
     * @param pattern   匹配模式
     * @return  Integer   20200904
     */
    public static Integer convertTime2Int(Integer timestamp, String pattern) {
        DateTime date = DateUtil.date(timestamp * 1000L);
        return Integer.parseInt(DateUtil.format(date, pattern));
    }

    /**
     * @param dateStr 2020-09-04
     * @param pattern   匹配模式
     * @return  Integer   20200904
     */
    public static Integer convertStr2Int(String dateStr, String pattern) {
        return Integer.parseInt(DateUtil.format(DateUtil.parse(dateStr), pattern));
    }

    /**
     * @param dateStr 2020-09-04
     * @param pattern   匹配模式
     * @return  String   20200904
     */
    public static String convertStr2Str(String dateStr, String pattern) {
        return DateUtil.format(DateUtil.parse(dateStr), pattern);
    }


    /**
     * 获取偏移后的时间
     * @param dateStr  字符串
     * @param offset   偏移
     * @return
     */
    public static Integer offset(String dateStr,Integer offset)
    {
        DateTime dateTime = DateUtil.parse(dateStr);
        return  (int)((DateUtil.offsetDay(dateTime, offset).getTime()) / 1000);
    }

    /**
     * 获取偏移后的时间
     * @param dateStr  日期字符串
     * @param offset   偏移
     * @return
     */
    public static String offset(String dateStr,Integer offset,String pattern)
    {
        DateTime dateTime = DateUtil.parse(dateStr);
        return  convertTime2Str((int) ((DateUtil.offsetDay(dateTime, offset).getTime()) / 1000),pattern);
    }

    /**
     * 获取偏移后的时间
     * @param timeStamp  时间戳
     * @param offset   偏移
     * @return
     */
    public static Integer offsetHour(Integer timeStamp,Integer offset)
    {
        DateTime dateTime = DateUtil.parse(convertTime2Str(timeStamp));
        return   (int)((DateUtil.offsetHour(dateTime,offset).getTime()) / 1000);
    }
    /**
     * 获取偏移后的时间
     * @param timestamp  时间戳
     * @param offset   偏移
     * @return
     */
    public static String offset(Integer timestamp,Integer offset,String pattern)
    {

        DateTime dateTime = DateUtil.parse(convertTime2Str(timestamp));
        return  convertTime2Str((int) ((DateUtil.offsetDay(dateTime, offset).getTime()) / 1000),pattern);
    }


    /**

     * 获取时间段内的日期
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param hourOrDay  按小时或天  1或 24
     * @param pattern    匹配模式
     * @return  [2019-10-15:5,2019-10-15:6]
     */
    public static Set<String> getDatesListByStartTimeAndEndTime(String startTime, String endTime, Integer hourOrDay, String pattern)
    {
        Integer dStart = convertStr2Time(startTime);
        Integer dEnd = convertStr2Time(endTime);
        Set<String> datesStrings = new TreeSet<>();

        while (dStart <= dEnd){
            datesStrings.add(DateUtils.convertTime2Str(dStart,pattern));
            dStart = HOUR_TIME.equals(hourOrDay) ? dStart + HOUR_TIME * 3600: dStart + DAY_TIME * 3600;
        }
        return datesStrings;
    }

    /**
     * 获取时间段内的在周几的日期
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pattern   匹配模式
     * @param weekdays   [1,2,3,4,5,6,7] 1-代表周日 2-代表周二....以此类推
     * @return
     */
    public static Set<String> getWeekDayDatesListByStartTimeAndEndTime(String startTime, String endTime, Integer hourOrDay,String pattern, List<Integer> weekdays){
        Set<String> datesStrings = new TreeSet<>();
        Set<String> datesListByStartTimeAndEndTime = DateUtils.getDatesListByStartTimeAndEndTime(startTime, endTime, hourOrDay, pattern);
        if (CollectionUtil.isNotEmpty(datesListByStartTimeAndEndTime) && CollectionUtil.isNotEmpty(weekdays)){
            for (String s : datesListByStartTimeAndEndTime) {
                if (weekdays.contains(DateUtil.dayOfWeek(DateUtil.parse(s)))){
                    datesStrings.add(s);
                }
            }
        }
        return datesStrings;
    }


    /**
     * 获取俩个日期相差几天
     * @param beginDate  20200917
     * @param endDate   20200918
     * @return 1
     */
    public static int getformatBetween(String beginDate,String endDate){
        if (beginDate == null){
            beginDate = DateUtils.convertTime2Str(DateUtils.getCurrentTime(),DateUtils.DATE_NOFUll_FORMAT);
        }

        int anInt = Integer.parseInt(String.valueOf(DateUtil.between(
                DateUtil.parse(beginDate, DateUtils.DATE_NOFUll_FORMAT),
                DateUtil.parse(endDate, DateUtils.DATE_NOFUll_FORMAT),
                DateUnit.DAY, true)));
        if (Integer.parseInt(beginDate) >  Integer.parseInt(endDate) ){
            return anInt*(-1);
        }
        return anInt;
    }


    /**
     * 获取当前时间的整点，转化为时间戳
     * 2019-06-30 12:48:25, 则结果为 2019-06-30 12:00:00 对应的时间戳
     * @return
     */
    public static int getHourOfTime(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(date);
        return convertStr2Time(str);
    }

    /**
     * 从时间戳中获取纯数字日期
     * @param timestamp
     * @return
     */
    public static int getDateOfTimestamp(Integer timestamp){
        String dateTime = DateUtils.convertTime2Str(timestamp);
        return Integer.parseInt(dateTime.substring(0,10).replace("-",""));
    }


    /**
     * 从时间戳中获取纯数字小时
     * @param timestamp
     * @return
     */
    public static int getHourOfTimestamp(Integer timestamp){
        String dateTime = DateUtils.convertTime2Str(timestamp);
        return Integer.parseInt(dateTime.substring(11,13));
    }

    /**
     * 获取偏移offset小时后的日期（8位数字）
     * 2019-09-01 00:26:23 对应时间戳向前偏移1小时
     * 结果为 20190831
     * @param timestamp
     * @param offset
     * @return
     */
    public static int getDateOffSet(Integer timestamp,int offset){
        int time = DateUtils.offsetHour(timestamp,offset);
        return getDateOfTimestamp(timestamp);
    }

    /**
     * 获取某一天的起始时间字符串
     * @param dateStr 某一天
     * @return  2019-09-10 00:00:00
     */
    public static String getBeginOfDay(String dateStr)
    {
        Date date = DateUtil.parse(dateStr);
        return  DateUtil.beginOfDay(date).toString();
    }

    /**
     * 获取某一天的结束时间字符串
     * @param dateStr 某一天
     * @return  2019-09-10 23:59:59
     */
    public static String getEndOfDay(String dateStr)
    {
        Date date = DateUtil.parse(dateStr);
        return  DateUtil.endOfDay(date).toString();
    }

    /**
     * 获取昨天的日期，格式：20191001
     * @return
     */
    public static Integer getYesterday(){
        String date = DateUtil.yesterday().toString();
        Integer integer = convertStr2Time(date);
        return getDateOfTimestamp(integer);
    }

    /**
     * 判断某个时间戳在某个时间端内
     * @param nowDate   时间戳
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @return
     */
    public static boolean isInTimeLine(Integer nowDate, Integer startTime, Integer endTime){
        if(nowDate > startTime && nowDate < endTime){
            return true;
        }
        return  false;
    }

    /**
     * htool工具转换成现实对应的周几
     * @param day
     * @return
     */
    public static Integer convertWeek(Integer day){
        Integer now;
        if (day == 1){
            now = 7;
        }else{
            now = day -1;
        }
        return now;
    }


private DateUtils() {
}
}
