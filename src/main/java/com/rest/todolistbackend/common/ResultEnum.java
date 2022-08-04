package com.rest.todolistbackend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(200, "success"),
    FAIL(400, "fail"),
    NOT_FOUND(404,"not found"),
    SERVER_ERROR(500, "server error");
    private Integer code;
    private String message;
}
