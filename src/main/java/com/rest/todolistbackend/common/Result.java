package com.rest.todolistbackend.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> {

    private int code;

    private String message;

    private T data;

    public Result() {
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        return result;
    }

    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.FAIL.getCode());
        result.setMessage(ResultEnum.FAIL.getMessage());
        return result;
    }
}
