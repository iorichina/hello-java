package iorichina.hellojava.helloface.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author qinshanmo 2016年4月8日
 */
public class DateUtil {

    public static final int MILLIS_PER_SECOND = 1000;
    public static final int SECONDS_IN_ONE_DAY = 86400;
    private static final String YYYY_MM = "yyyyMM";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YYYY_MM_DD_NUMBER = "yyyyMMdd";
    private static final ThreadLocal<DateFormat> NUMBER_MONTH = getThreadLocalDateFormater(YYYY_MM);
    private static final ThreadLocal<DateFormat> YEAR_MONTH_DATE = getThreadLocalDateFormater(YYYY_MM_DD);
    private static final ThreadLocal<DateFormat> YEAR_MONTH_DATE_NUMBER = getThreadLocalDateFormater(YYYY_MM_DD_NUMBER);

    private static ThreadLocal<DateFormat> getThreadLocalDateFormater(String pattern) {
        return ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
    }

    /**
     * 返回如201411
     *
     * @param date
     * @return
     */
    public static int getNumberYearMonth(final Date date) {
        return Integer.valueOf(NUMBER_MONTH.get().format(date));
    }

    /**
     * 返回如2014-11-25
     *
     * @param date
     * @return
     */
    public static String getYearMonthDateStr(final Date date) {
        return YEAR_MONTH_DATE.get().format(date);
    }

    /**
     * 返回如20141125
     *
     * @param timestamp
     * @return
     */
    public static String getYearMonthDateNumberStr(final Integer timestamp) {
        Date date = new Date(Integer.toUnsignedLong(timestamp) * MILLIS_PER_SECOND);
        return YEAR_MONTH_DATE_NUMBER.get().format(date);
    }

    public static String getYearMonthDateNumberStr(final Date date) {
        return YEAR_MONTH_DATE_NUMBER.get().format(date);
    }

    /**
     * 返回如20141125
     *
     * @param date
     * @return
     */
    public static Integer getYearMonthDateNumber(final Date date) {
        return Integer.valueOf(YEAR_MONTH_DATE_NUMBER.get().format(date));
    }

    public static int getTimestampInDayFirstSecond(final Calendar calendar) {
        Calendar other = (Calendar) calendar.clone();
        other.set(Calendar.HOUR_OF_DAY, 0);
        other.set(Calendar.MINUTE, 0);
        other.set(Calendar.SECOND, 0);
        other.set(Calendar.MILLISECOND, 0);
        return getTimestamp(other.getTimeInMillis());
    }

    public static int getTimestampInDayFirstSecond(int ts) {
        Calendar other = Calendar.getInstance();
        other.setTimeInMillis(Integer.toUnsignedLong(ts) * MILLIS_PER_SECOND);
        other.set(Calendar.HOUR_OF_DAY, 0);
        other.set(Calendar.MINUTE, 0);
        other.set(Calendar.SECOND, 0);
        other.set(Calendar.MILLISECOND, 0);
        return getTimestamp(other.getTimeInMillis());
    }

    public static int getTimestampInDayFirstSecond(long timeInMillis) {
        Calendar other = Calendar.getInstance();
        other.setTimeInMillis(timeInMillis);
        other.set(Calendar.HOUR_OF_DAY, 0);
        other.set(Calendar.MINUTE, 0);
        other.set(Calendar.SECOND, 0);
        other.set(Calendar.MILLISECOND, 0);
        return getTimestamp(other.getTimeInMillis());
    }

    public static int getTimestampInDayLastSecond(final Calendar calendar) {
        Calendar other = (Calendar) calendar.clone();
        other.add(Calendar.DATE, 1);
        other.set(Calendar.HOUR_OF_DAY, 0);
        other.set(Calendar.MINUTE, 0);
        other.set(Calendar.SECOND, 0);
        other.set(Calendar.MILLISECOND, 0);
        return getTimestamp(other.getTimeInMillis()) - 1;
    }

    public static int getTimestampInDayLastSecond(int ts) {
        Calendar other = Calendar.getInstance();
        other.setTimeInMillis(Integer.toUnsignedLong(ts) * MILLIS_PER_SECOND);
        other.add(Calendar.DATE, 1);
        other.set(Calendar.HOUR_OF_DAY, 0);
        other.set(Calendar.MINUTE, 0);
        other.set(Calendar.SECOND, 0);
        other.set(Calendar.MILLISECOND, 0);
        return getTimestamp(other.getTimeInMillis()) - 1;
    }

    public static int getTimestampInDayLastSecond(long timeInMillis) {
        Calendar other = Calendar.getInstance();
        other.setTimeInMillis(timeInMillis);
        other.add(Calendar.DATE, 1);
        other.set(Calendar.HOUR_OF_DAY, 0);
        other.set(Calendar.MINUTE, 0);
        other.set(Calendar.SECOND, 0);
        other.set(Calendar.MILLISECOND, 0);
        return getTimestamp(other.getTimeInMillis()) - 1;
    }

    public static int addTimestampByOneDay(final Integer timestamp) {
        return timestamp + SECONDS_IN_ONE_DAY;
    }

    public static int getTimestamp() {
        return (int) (System.currentTimeMillis() / MILLIS_PER_SECOND);
    }

    public static int getTimestamp(long timeInMillis) {
        return (int) (timeInMillis / MILLIS_PER_SECOND);
    }

    public static int getTimestamp(final Date date) {
        return (int) (date.getTime() / MILLIS_PER_SECOND);
    }

    /**
     * /**
     *
     * @return
     */
    public static String getYestMonth() {
        return new SimpleDateFormat(YYYY_MM).format(new Date());
    }

    /**
     * /**
     *
     * @return
     */
    public static long getUnixTime() {
        return (System.currentTimeMillis() / MILLIS_PER_SECOND);
    }

    public static void main(String[] args) {
        System.out.println(getYestMonth());
    }

}
