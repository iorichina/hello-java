package iorichina.hellojava.hellospringboot.exception;

/**
 * Created by iorichina on 2017/1/17.
 */
public abstract class AppException extends Exception {
    private int code;

    public AppException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
