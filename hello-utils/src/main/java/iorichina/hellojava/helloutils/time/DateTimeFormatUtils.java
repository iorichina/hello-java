package iorichina.hellojava.helloutils.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by iorihuang on 2016/12/1.
 */
public class DateTimeFormatUtils {
    public static final String ISO_DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String NORMAL_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String RFC3339_DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String NUMBER_DATEFORMAT = "yyyyMMddHHmmss";

    private static final ThreadLocal<DateFormat> ISO_DATE_TIME = getThreadLocalDateFormater(ISO_DATEFORMAT);
    private static final ThreadLocal<DateFormat> NORMAL_DATE_TIME = getThreadLocalDateFormater(NORMAL_DATEFORMAT);
    private static final ThreadLocal<DateFormat> RFC3339_DATE_TIME = getThreadLocalDateFormater(RFC3339_DATEFORMAT);
    private static final ThreadLocal<DateFormat> NUMBER_DATE_TIME = getThreadLocalDateFormater(NUMBER_DATEFORMAT);

    private static ThreadLocal getThreadLocalDateFormater(String pattern) {
        return new ThreadLocal<DateFormat>() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat(pattern);
            }
        };
    }

    /**
     * 返回如2014-11-20 12:00:00
     *
     * @param date
     * @return
     */
    public static String getSimpleTimeString(Date date) {
        return NORMAL_DATE_TIME.get().format(date);
    }

    /**
     * 返回如2014-11-20T12:00:00.005Z
     *
     * @param date
     * @return
     */
    public static String getIsoTimeString(Date date) {
        return ISO_DATE_TIME.get().format(date);
    }

    /**
     * 返回如2014-11-20T12:00:00Z
     *
     * @param date
     * @return
     */
    public static String getRFC3339TimeString(Date date) {
        return RFC3339_DATE_TIME.get().format(date);
    }

    /**
     * 返回如20141120120000
     *
     * @param date
     * @return
     */
    public static String getNumberTimeString(Date date) {
        return NUMBER_DATE_TIME.get().format(date);
    }

    public static void main(String[] args) {
        Date timestamp = new Date();
        System.out.println("DateTimeFormatUtils.getSimpleTimeString(timestamp)");
        System.out.println(DateTimeFormatUtils.getSimpleTimeString(timestamp));
        System.out.println("DateTimeFormatUtils.getIsoTimeString(timestamp)");
        System.out.println(DateTimeFormatUtils.getIsoTimeString(timestamp));
        System.out.println("DateTimeFormatUtils.getRFC3339TimeString(timestamp)");
        System.out.println(DateTimeFormatUtils.getRFC3339TimeString(timestamp));
        System.out.println("DateTimeFormatUtils.getNumberTimeString(timestamp)");
        System.out.println(DateTimeFormatUtils.getNumberTimeString(timestamp));
    }
}
