package com.rest.todolistbackend.exception;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException() {
        super("TodoNotFoundException");
    }
}
