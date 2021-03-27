package iorichina.hellojava.hellosample.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateParser {
    private static final ThreadLocal<SimpleDateFormat> ISO_DATE = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * @param startDate 2017-06-05
     * @param endDate   2017-06-05
     * @return now time is between start and end compared by local date
     */
    public static boolean isNowBetweenLocalDate(String startDate, String endDate) throws DateTimeParseException {
        boolean result;
        {
            LocalDate now = LocalDate.now();
            result = !(now.isBefore(LocalDate.parse(startDate)) || now.isAfter(LocalDate.parse(endDate)));
        }
        return result;
    }

    /**
     * @param startDate 2017-06-05
     * @param endDate   2017-06-05
     * @return now time is between start and end compared by milliseconds from epoch time
     */
    public static boolean isNowBetween(String startDate, String endDate) throws ParseException {
        boolean result;
        {
            long now = System.currentTimeMillis();
            result = now < ISO_DATE.get().parse(startDate).getTime() || now > ISO_DATE.get().parse(endDate).getTime();
        }
        return result;
    }
    /**
     * @param startDate 2017-06-05
     * @param endDate   2017-06-05
     * @return now time is between start and end compared by milliseconds from epoch time
     */
    public static boolean isNowBetweenSimpleDate(String startDate, String endDate) throws ParseException {
        boolean result;
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            long now = System.currentTimeMillis();
            result = now < sdf.parse(startDate).getTime() || now > sdf.parse(endDate).getTime();
        }
        return result;
    }
}
