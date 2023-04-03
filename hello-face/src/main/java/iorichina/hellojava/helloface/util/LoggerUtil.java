package iorichina.hellojava.helloface.util;

import iorichina.hellojava.helloface.exception.AppException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by iorichina on 2017/5/22.
 */
public class LoggerUtil {
    public static void none(Exception e) {
    }

    public static String getMessage(AppException e) {
        return e.getMessage() + " " + StringUtils.trimToEmpty(e.getError());
    }

    public static String getMessage(Exception e) {
        return e.getMessage();
    }
}
