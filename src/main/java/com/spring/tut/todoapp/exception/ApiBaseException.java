package com.spring.tut.todoapp.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiBaseException extends RuntimeException{

    public ApiBaseException(final String message) {
        super(message);
    }

    public abstract HttpStatus getStatusCode();
}
