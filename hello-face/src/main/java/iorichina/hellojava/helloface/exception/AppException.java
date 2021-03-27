package iorichina.hellojava.helloface.exception;

import iorichina.hellojava.helloface.dto.AppCode;

/**
 * Created by iorihuang on 2017/1/17.
 */
public class AppException extends Exception {
    public int code;
    public String error;

    public AppException setCode(int code) {
        this.code = code;
        return this;
    }

    public String getError() {
        return error;
    }

    public AppException setError(String error) {
        this.error = error;
        return this;
    }

    public int getCode() {
        return code;
    }

    public AppException(int code, String message) {
        super(message);
        this.code = code;
    }

    public AppException(AppCode appCode) {
        super(appCode.desc);
        this.code = appCode.code;
    }

    public static AppException getException(AppCode appCode) {
        return new AppException(appCode);
    }
}
