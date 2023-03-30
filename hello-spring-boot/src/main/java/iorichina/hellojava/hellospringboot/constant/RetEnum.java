package iorichina.hellojava.hellospringboot.constant;


/**
 * 返回码
 */
public enum RetEnum {
    SUCCESS(0),
    /**
     * 未知错误
     */
    UNKNOWN_ERROR(1000),
    /**
     * 参数错误
     */
    PARAMS_ERROR(1001),
    /**
     * 服务器内部错误
     */
    SERVER_ERROR(1002),
    /**
     * 超时
     */
    SERVER_TIMEOUT(1003),
    /**
     * 远程请求错误
     */
    RPC_ERROR(1004);

    private final int value;

    private RetEnum(int value) {
        this.value = value;
    }

    /**
     * Find a the enum type by its integer value, as defined in the Thrift IDL.
     *
     * @return null if the value is not found.
     */
    public static RetEnum findByValue(int value) {
        switch (value) {
            case 0:
                return SUCCESS;
            case 1000:
                return UNKNOWN_ERROR;
            case 1001:
                return PARAMS_ERROR;
            case 1002:
                return SERVER_ERROR;
            case 1003:
                return SERVER_TIMEOUT;
            case 1004:
                return RPC_ERROR;
            default:
                return null;
        }
    }

    /**
     * Get the integer value of this enum value, as defined in the Thrift IDL.
     */
    public int getValue() {
        return value;
    }
}
