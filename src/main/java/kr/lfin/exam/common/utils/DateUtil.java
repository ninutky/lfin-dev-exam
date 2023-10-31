package kr.lfin.exam.common.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 2022311
     */
    public static String YYYY_MM_DD = "yyyyMMdd";
    /**
     * 2022-06-01
     */
    public static String YYYY__MM__DD = "yyyy-MM-dd";
    /**
     * 2004/03/11
     */
    public static String YYYY___MM___DD = "yyyy/MM/dd";
    /**
     * 040311
     */
    public static String YY_MM_DD = "yyMMdd";
    /**
     * 04-03-11
     */
    public static String YY__MM__DD = "yy-MM-dd";
    /**
     * 화
     */
    public static String E = "E";
    /**
     * 2004-03-11(목)
     */
    public static String YYYY__MM__DD_E = "yyyy-MM-dd(E)";
    /**
     * 04-03-11(목)
     */
    public static String YY__MM__DD_E = "yy-MM-dd(E)";
    /**
     * 20040311123423
     */
    public static String YYYY_MM_DD_HH_MM_SS = "yyyyMMddHHmmss";
    /**
     * 20040311123423
     */
    public static String YYYY_MM_DD_HH_MM = "yyyyMMddHHmm";
    /**
     * 2004-03-11 12:34:23
     */
    public static String YYYY__MM__DD__HH__MM__SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 040311123423
     */
    public static String YY_MM_DD_HH_MM_SS = "yyMMddHHmmss";
    /**
     * 04-03-11 12:34:23
     */
    public static String YY__MM__DD__HH__MM__SS = "yy-MM-dd HH:mm:ss";
    public static String YYYY__MM__DD__A__HH___MM__SS = "yyyy-MM-dd a hh:mm:ss";
    /**
     * 20040311123423000
     */
    public static String YYYY_MM_DD_HH_MM_SS_SS = "yyyyMMddHHmmssSS";
    /**
     * 2004-03-11 12:34:23.123
     */
    public static String YYYY__MM__DD__HH__MM__ss__SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 2004031112
     */
    public static String YYYYMMDDHH = "yyyyMMddHH";

    public static String YYYY = "yyyy";
    public static String YYYY_MM = "yyyyMM";
    public static String YYYY__MM = "yyyy/MM";

    /**
     * 123423
     */
    public static String HH_MM_SS = "HHmmss";
    /**
     * 12:34:23
     */
    public static String HH__MM__SS = "HH:mm:ss";

    /**
     * 1234
     */
    public static String HH_MM = "HHmm";
    /**
     * 12:34
     */
    public static String HH__MM = "HH:mm";

    public static String TIME_ZONE_SEOUL = "Asia/Seoul";
    public static String TIME_ZONE_UTC = "GMT+0";

    /**
     * 타임존기반으로 date 를 문자열로 리턴
     *
     * @param date
     * @param pattern
     * @param timeZone
     * @return
     */
    public static String getDateString(Date date, String pattern, String timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(java.util.TimeZone.getTimeZone(timeZone));
        return sdf.format(date);
    }

    /**
     * 타임존기반으로 현재시간의 문자열로 리턴
     *
     * @param pattern
     * @param timeZone
     * @return
     */
    public static String getNowToString(String pattern, String timeZone) {
        Date now = new Date();
        return getDateString(now, pattern, timeZone);
    }

    /**
     * 타임존기반으로 date 를 문자열로 리턴, 원하는 길이로 잘라 리턴
     *
     * @param date
     * @param pattern
     * @param timeZone
     * @param len
     * @return
     */
    public static String getDateString(Date date, String pattern, String timeZone, int len) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(java.util.TimeZone.getTimeZone(timeZone));
        String result = sdf.format(date);

        if (len > 1) {
            if (result.length() > len) {
                result = result.substring(0, len);
            }
        }
        return result;
    }

}