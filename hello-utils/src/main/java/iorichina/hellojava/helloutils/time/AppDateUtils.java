package iorichina.hellojava.helloutils.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class AppDateUtils {
    public static final ZoneOffset DEFAULT_OFFSET = OffsetDateTime.now().getOffset();
    public static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("yyyy");
    public static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");
    public static final DateTimeFormatter YEAR_MONTH_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter YEAR_MONTH_DATE_HOUR_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHH");
    public static final DateTimeFormatter YEAR_MONTH_DATE_HOUR_MINUTE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    public static final DateTimeFormatter YEAR_MONTH_DATE_HOUR_MINUTE_SECOND_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter HOUR_MINUTE_SECOND_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
    public static final DateTimeFormatter MILLIS_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_TIME_HOUR_FORMATTER = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH点");
    public static final DateTimeFormatter HOUR_WITH_MINUTE_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter HOUR_WITH_MINUTE_SECOND_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static long toMinutelyEpochSecond(long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault()).truncatedTo(ChronoUnit.MINUTES).toEpochSecond(DEFAULT_OFFSET);
    }

    /**
     * 将毫秒级时间戳转换成int类型的年月格式：yyyy，如2020
     */
    public static int toY(long milliSeconds) {
        return Integer.parseInt(YEAR_FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.systemDefault())));
    }

    /**
     * 将毫秒级时间戳转换成int类型的年月格式：yyyyMM，如202010
     */
    public static int toYm(long milliSeconds) {
        return Integer.parseInt(YEAR_MONTH_FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.systemDefault())));
    }

    public static int toYm(LocalDate ld) {
        return Integer.parseInt(YEAR_MONTH_FORMATTER.format(ld));
    }

    public static int nowYm() {
        return Integer.parseInt(YEAR_MONTH_FORMATTER.format(LocalDateTime.now()));
    }

    public static int lastYm(long milliSeconds, int x) {
        return Integer.parseInt(YEAR_MONTH_FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.systemDefault()).minusMonths(x)));
    }

    public static int lastYm(int ym, int x) {
        YearMonth yearMonth = fromYm(ym).minusMonths(x);
        return (yearMonth.getYear() * 100) + yearMonth.getMonthValue();
    }

    /**
     * 将毫秒级时间戳转换成int类型的年月日格式：yyyyMMdd，如20201014
     */
    public static int toYmd(long milliSeconds) {
        return Integer.parseInt(YEAR_MONTH_DATE_FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.systemDefault())));
    }

    public static int toYmd(LocalDate ld) {
        return Integer.parseInt(YEAR_MONTH_DATE_FORMATTER.format(ld));
    }

    public static long toYmdH(long milliSeconds) {
        return toYmdH(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.systemDefault()));
    }

    public static long toYmdH(LocalDateTime ldt) {
        return Long.parseLong(YEAR_MONTH_DATE_HOUR_FORMATTER.format(ldt));
    }

    public static long toYmdHm(long milliSeconds) {
        return toYmdHm(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.systemDefault()));
    }

    public static long toYmdHm(LocalDateTime ldt) {
        return Long.parseLong(YEAR_MONTH_DATE_HOUR_MINUTE_FORMATTER.format(ldt));
    }

    public static long toYmdHms(long milliSeconds) {
        return toYmdHm(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.systemDefault()));
    }

    public static long toYmdHms(LocalDateTime ldt) {
        return Long.parseLong(YEAR_MONTH_DATE_HOUR_MINUTE_SECOND_FORMATTER.format(ldt));
    }

    public static YearMonth fromYm(int ym) {
        return YearMonth.of(ym / 100, ym % 100);
    }

    public static LocalDate fromYmd(int ymd) {
        int y = ymd / 10000;
        int m = ymd / 100 - y * 100;
        int d = ymd - y * 10000 - m * 100;
        return LocalDate.of(y, m, d);
    }

    public static LocalDateTime fromYmdHms(long ymdHms) {
        long y = ymdHms / 1_00_00_00_00_00L;
        long m = (ymdHms / 1_00_00_00_00L) % 100;
        long d = (ymdHms / 1_00_00_00L) % 100;
        long h = (ymdHms / 1_00_00L) % 100;
        long _m = (ymdHms / 1_00L) % 100;
        long s = ymdHms % 100;
        return LocalDateTime.of((int) y, (int) m, (int) d, (int) h, (int) _m, (int) s);
    }

    /**
     * 将毫秒级时间戳转换成int类型的时分秒格式：HHmmss，如110510
     */
    public static int toHms(long milliSeconds) {
        return Integer.parseInt(HOUR_MINUTE_SECOND_FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.systemDefault())));
    }

    public static int toHms(LocalDateTime ldt) {
        return Integer.parseInt(HOUR_MINUTE_SECOND_FORMATTER.format(ldt));
    }

    public static String toHourWithMinute(long secondsFromDayStart) {
        return LocalTime.ofSecondOfDay(secondsFromDayStart).format(HOUR_WITH_MINUTE_FORMATTER);
    }

    /**
     * HH:mm:ss
     */
    public static String toHourWithMinuteSecond(long nowMillis) {
        return LocalTime.now().format(HOUR_WITH_MINUTE_SECOND_FORMATTER);
    }

    public static long minTimeOfDate(long timeMillis) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), ZoneId.systemDefault()).truncatedTo(ChronoUnit.DAYS).toInstant().toEpochMilli();
    }

    /**
     * 2020-11-14 21:28:46.267
     */
    public static String formatMillis(long milliSeconds) {
        return MILLIS_TIME_FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.systemDefault()));
    }

    /**
     * 2020-11-14 21:28:46
     */
    public static String formatSeconds(long seconds) {
        return DATE_TIME_FORMATTER.format(LocalDateTime.ofEpochSecond(seconds, 0, DEFAULT_OFFSET));
    }

    /**
     * 2020-11-14 21:28:46
     */
    public static String formatDateTime(long milliSeconds) {
        return DATE_TIME_FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.systemDefault()));
    }

    public static String formatDateTime(LocalDateTime ldt) {
        return DATE_TIME_FORMATTER.format(ldt);
    }

    /**
     * yyyy年MM月dd日HH点
     */
    public static String formatDateTimeHour(long milliSeconds) {
        return DATE_TIME_HOUR_FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.systemDefault()));
    }

    public static int toWeeklyYmd(long milliSeconds) {
        return Integer.parseInt(YEAR_MONTH_DATE_FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.systemDefault()).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))));
    }

    public static long toMillis(LocalDateTime ldt) {
        return ldt.toInstant(DEFAULT_OFFSET).toEpochMilli();
    }

    public static long toTenMinutesYmdHms(long millis) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
        return toTenMinutesYmdHms(localDateTime);
    }

    public static long toTenMinutesYmdHms(LocalDateTime localDateTime) {
        return toYmdHms(localDateTime.truncatedTo(ChronoUnit.MINUTES).withMinute((localDateTime.getMinute() / 10) * 10));
    }

    /**
     * 从unix时间开始，经过了xx年xx月xx日xx时xx分xx秒.xxx
     *
     * @param millis 从unix开始的毫秒数
     * @return [[[[[xx年]xx月]xx日]xx时]xx分]xx秒.xxx
     */
    public static String afterUnix(long millis) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault()).minusHours(8);
//        System.out.println(MILLIS_TIME_FORMATTER.format(localDateTime));
        StringBuilder sb = new StringBuilder();

        boolean append = false;
        int y = localDateTime.getYear() - 1970;
        if (y > 0) {
            sb.append(y).append("年");
            append = true;
        }
        int m = localDateTime.getMonthValue();
        if (append || m > 1) {
            sb.append(m - 1).append("月");
            append = true;
        }
        int d = localDateTime.getDayOfMonth();
        if (append || d > 1) {
            sb.append(d - 1).append("日");
            append = true;
        }
        int h = localDateTime.getHour();
        if (append || h > 0) {
            sb.append(h).append("时");
            append = true;
        }
        int mm = localDateTime.getMinute();
        if (append || mm > 0) {
            sb.append(mm).append("分");
        }
        int s = localDateTime.getSecond();
        sb.append(s).append("秒").append(".").append("%03d");
        return String.format(sb.toString(), localDateTime.getNano() / 1000_000);
    }

    public static long toMillis(String yyyyMMddHHmmss) {
        return toMillis(LocalDateTime.parse(yyyyMMddHHmmss, DATE_TIME_FORMATTER));
    }

    /**
     * 兼容年月、年月日、年月日时分秒
     */
    public static int getYm(long ymd) {
        if (ymd < 9999_99) {
            return (int) (ymd);
        }
        if (ymd < 9999_99_99) {
            return (int) (ymd / 1_00);
        }
        //9999_99_99_99_99_99
        return (int) (ymd / 1_00_00_00_00);
    }

}
