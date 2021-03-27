package iorichina.hellojava.helloface.dto;

public enum AppCode {
    SUCCESS(0, "success"),
    FAIL(1, "fail"),
    INTERNAL_SERVER_ERROR(500, "internal server error"),
    ILLEGAL_PARAMETER(400, "illegal parameter");
    public int code;
    public String desc;

    AppCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
