package iorichina.hellojava.hellobaidu;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloUtils {
    private static final String NORMAL_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final ThreadLocal<DateFormat> NORMAL_DATE_TIME = getThreadLocalDateFormater(NORMAL_DATEFORMAT);

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

    public static String content(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return getSimpleTimeString(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return new CellReference(cell).formatAsString();
        }
    }
}
