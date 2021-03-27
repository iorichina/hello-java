package iorichina.hello.spring.boot.exception;

import iorichina.hello.spring.boot.constant.RetEnum;

/**
 * Created by iorihuang on 2016/12/7.
 */
public class IpIpException extends AppException {

    public IpIpException(int code, String message) {
        super(code, message);
    }

    public static IpIpException getException(RetEnum retEnum, String msg) {
        return new IpIpException(retEnum.getValue(), msg);
    }

}