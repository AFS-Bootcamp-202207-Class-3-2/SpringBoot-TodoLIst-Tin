package com.rest.todolistbackend.controller.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoVO {

    private String text;

    private Boolean done = false;
}
