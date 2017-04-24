package chingtech.library.utils;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import chingtech.library.R;

public class TimeUtils {

    public static final String TZ_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String T_HOUR_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_HOUR_MIN_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_MONTH_FORMAT = "MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_SHAORT_FORMAT = "MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String TIME_SHAORT_FORMAT = "HH:mm";
    public static final String TIME_MS_FORMAT = "mm:ss";
    public static final String Y_M_FORMAT = "yyyy-MM";
    public static final String Y_FORMAT = "yyyy";
    public static final String M_FORMAT = "MM";
    public static final String D_FORMAT = "dd";
    public static final String H_24_FORMAT = "HH";
    public static final String H_12_FORMAT = "hh";
    public static final String MIN_FORMAT = "mm";
    public static final String S_FORMAT = "ss";
    public static final String DATE_CALENDAR = "yyyyMMdd";
    public static final String DATE_E_FORMAT = "yyyy-MM-dd E";
    public static final String DATE_FORMAT_CN = "yyyy年MM月dd日";
    public static final String DATE_SHAORT_FORMAT_CN = "MM月dd日";
    public static final String DAY_FORMAT = "MM/dd";
    public static final String DAY_YMD_FORMAT = "yyyy/MM/dd";

    public static final int WEEKS = 0;
    public static final int WEEKS_CN = 1;
    public static final int WEEKS_EN = 2;
    public static final int WEEKS_EN_ABB = 3;
    public static final int WEEKS_EN_LET = 4;

    public static SimpleDateFormat getFormat(String format){
        return new SimpleDateFormat(format);
    }

    /**
     * 格式化时间
     *
     * @param strDateTime
     * @param format
     * @return
     */
    public static String formatDate(String strDateTime, String format) {
        Date date = strToDate(strDateTime);
        return getFormat(format).format(date);
    }

    /**
     * 格式化时间
     *
     * @param strDateTime
     * @param format
     * @return
     */
    public static String formatDateTime(String strDateTime, String format) {
        Date date = strToDateTime(strDateTime);
        return getFormat(format).format(date);
    }

    /**
     * 格式化时间
     *
     * @param dateTime
     * @param format
     * @return
     */
    public static String formatDateTime(Date dateTime, String format) {
        return getFormat(format).format(dateTime);
    }

    /**
     * 格式化时间
     *
     * @param dateTime
     * @param format
     * @return
     */
    public static String formatDateTime(long dateTime, String format) {
        Date date = new Date(dateTime);
        return getFormat(format).format(date);
    }

    /**
     * 将日期字符串转换为date日期
     *
     * @param strDate
     * @return date型 yyyy-MM-dd
     */
    public static Date strToDate(String strDate) {
        Date date = null;
        try {
            date = getFormat(DATE_FORMAT).parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将日期时间字符串转换为DateTime类型
     *
     * @param strDateTime
     * @return date yyyy-MM-dd HH:mm:ss
     */
    public static Date strToDateTime(String strDateTime) {
        Date datetime = null;
        try {
            datetime = getFormat(DATE_TIME_FORMAT).parse(strDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datetime;
    }

    /**
     * 将Date类型转化为String类型
     *
     * @param date
     * @param formatType
     * @return
     */
    public static String dateToStr(Date date, String formatType) {
        return getFormat(formatType).format(date);
    }

    /**
     * 将String类型转化为Date类型
     *
     * @param strDate
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String strDate, String formatType)
            throws ParseException {
        return getFormat(formatType).parse(strDate);
    }

    /**
     * 将String类型的日期 时间转换成long型
     *
     * @param strDateTime
     * @return
     * @throws ParseException
     */
    public static long strToLong(String strDateTime) throws ParseException {
        Date date = strToDateTime(strDateTime);
        return date.getTime();
    }

    /**
     * 获取当前日期 时间
     *
     * @param formatType 格式
     * @return
     */
    public static String getNowDateTime(String formatType){
        return dateToStr(new Date(), formatType);
    }

    /**
     * 获取当前日期 时间
     *
     * @return
     */
    public static long getNowDateTime(){
        return System.currentTimeMillis();
    }

    /**
     * 根据日期取得星期几
     *
     * @param strDate
     * @return
     */
    public static String getWeek(Context context, String strDate, int type){
        String[] weeks;
        switch (type) {
            case WEEKS:
                weeks = context.getResources().getStringArray(R.array.weekdays);
                break;
            case WEEKS_CN:
                weeks = context.getResources().getStringArray(R.array.weekdays_cn);
                break;
            case WEEKS_EN_ABB:
                weeks = context.getResources().getStringArray(R.array.weekdays_en_abb);
                break;
            case WEEKS_EN:
                weeks = context.getResources().getStringArray(R.array.weekdays_en);
                break;
            case WEEKS_EN_LET:
                weeks = context.getResources().getStringArray(R.array.weekdays_en_let);
                break;
            default:
                weeks = context.getResources().getStringArray(R.array.weekdays);
                break;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(strToDate(strDate));
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index < 0){
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * 根据指定日期计算所在周的周一到周日的日期
     *
     * @param strDate
     * @param week
     * @return
     * @throws ParseException
     */
    public static String getWeekDay(String strDate, int week) throws ParseException {
        String weekday;

        if (week < 0 || week > 6) {
            weekday = "Unknown";
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(strToDate(strDate));

            //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
            int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
            if (1 == dayWeek) {
                cal.add(Calendar.DAY_OF_MONTH, -1);
            }

            cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

            int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
            cal.add(Calendar.DATE, week);
            weekday = getFormat(DATE_FORMAT).format(cal.getTime());
        }
        return weekday;
    }

    /**
     * 由出生日期获得年龄
     *
     * @param birthDay
     * @return
     */
    public static int getAge(String birthDay) {
        Date birthday = strToDate(birthDay);

        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthDay is before Now. It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdatetime
     *
     * @return
     */
    public static String friendlyTime(String sdatetime) {
        Date time = strToDateTime(sdatetime);

        if (time == null) {
            return "Unknown";
        }

        String ftime;
        Calendar cal = Calendar.getInstance();

        // 今天日期
        String curDate = getFormat(DATE_FORMAT_CN).format(cal.getTime());
        // 服务器返回日期
        String paramDate = getFormat(DATE_FORMAT_CN).format(time);

        // 两个时间间隔（单位秒）
        long timeInterval = (cal.getTimeInMillis() - time.getTime()) / 1000;

        // 判断是否是同一天
        if (curDate.equals(paramDate)) {
            int hour = (int) (timeInterval / 3600);
            if (hour == 0)
                ftime = Math.max(timeInterval / 60, 1) + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        int days = (int) (timeInterval / 86400);
        if (days == 0) {
            int hour = (int) (timeInterval / 3600);
            if (hour == 0)
                ftime = Math.max(timeInterval / 60, 1) + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天 ";
        } else if (days > 2 && days < 31) {
            ftime = days + "天前";
        } else if (days >= 31 && days <= 2 * 31) {
            ftime = "一个月前";
        } else if (days > 2 * 31 && days <= 3 * 31) {
            ftime = "2个月前";
        } else if (days > 3 * 31 && days <= 4 * 31) {
            ftime = "3个月前";
        } else {
            ftime = getFormat(DATE_FORMAT_CN).format(time);
        }
        return ftime;
    }

    /**
     * 比较两个日期大小
     *
     * @param strDate1
     * @param strDate2
     * @return
     */
    public static boolean dateCompare(String strDate1, String strDate2) {
        Date d1 = strToDate(strDate1);
        Date d2 = strToDate(strDate2);

        //比较
        if ((d1.getTime() - d2.getTime()) / 1000 >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较两个时间大小
     *
     * @param strDateTime1
     * @param strDateTime2
     * @return
     */
    public static boolean dateTimeCompare(String strDateTime1, String strDateTime2) {
        Date d1 = strToDateTime(strDateTime1);
        Date d2 = strToDateTime(strDateTime2);

        //比较
        if ((d1.getTime() - d2.getTime()) / 1000 >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 当前日期前几天或者后几天的日期
     *
     * @param days
     * @return
     */
    public static String afterNDay(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, days);
        Date date = calendar.getTime();
        return getFormat(DATE_FORMAT).format(date);
    }

    /**
     * 当前日期前几天或者后几天的日期
     *
     * @param days
     * @param format
     * @return
     */
    public static String afterNDay(int days, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, days);
        Date date = calendar.getTime();
        return getFormat(format).format(date);
    }

    /**
     * 某日期之后days天的日期
     *
     * @param days
     * @param strDate
     * @return
     */
    public static String afterNDay(String strDate, int days) {
        Date date = strToDate(strDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        Date afterDate = calendar.getTime();
        return getFormat(DATE_FORMAT).format(afterDate);
    }

    /**
     * 某日期之后days天的日期
     *
     * @param days
     * @param strDate
     * @param format
     * @return
     */
    public static String afterNDay(String strDate, int days, String format) {
        Date date = strToDate(strDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        Date afterDate = calendar.getTime();
        return getFormat(format).format(afterDate);
    }

    /**
     * 判断是否润年
     *
     * 1.被400整除是闰年
     *
     * 2.不能被4整除则不是闰年
     *
     * 3.能被4整除同时不能被100整除则是闰年
     *
     * 4.能被4整除同时能被100整除则不是闰年
     *
     * @param strDate
     * @return
     */
    public static boolean isLeapYear(String strDate) {
        Date date = strToDate(strDate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }
}
