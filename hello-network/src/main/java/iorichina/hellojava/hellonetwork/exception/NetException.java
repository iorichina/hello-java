package iorichina.hellojava.hellonetwork.exception;

public class NetException extends Exception {
    public NetException(String message) {
        super(message, null, false, false);
    }

    public NetException(String message, Throwable cause) {
        super(message, cause, false, false);
    }
}
