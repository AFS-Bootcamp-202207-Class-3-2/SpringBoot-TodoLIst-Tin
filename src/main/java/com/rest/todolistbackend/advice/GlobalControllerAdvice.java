package com.rest.todolistbackend.advice;

import com.rest.todolistbackend.common.Result;
import com.rest.todolistbackend.common.ResultEnum;
import com.rest.todolistbackend.exception.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({TodoNotFoundException.class})
    public Result handleNotFoundException(Exception exception){
        return new Result(ResultEnum.NOT_FOUND.getCode(), exception.getMessage());
    }
}
