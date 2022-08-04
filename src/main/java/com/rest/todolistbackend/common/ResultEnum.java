package com.rest.todolistbackend.common;

public enum ResultEnum {

    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    SERVER_ERROR(500, "服务器异常");
    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
