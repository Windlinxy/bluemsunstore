package com.bluemsun.util;

public enum Status {
    SUCCESS(1, "成功！"),
    ERROR_NO_LOGIN(-1, "未登录！"),
    FAILED(0, "失败！"),
    ERROR_NO_AUTHORITY(-2,"没有权限操作！"),

    OP_SUCCESS(1,"操作成功！"),
    OP_FAILED(0,"操作失败！"),
    OP_EXCEPTION(-1,"有异常发生！")
    ;

    private final Integer code;
    private final String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
