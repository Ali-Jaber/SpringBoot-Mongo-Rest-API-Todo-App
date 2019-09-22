package com.spring.tut.todoapp.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiBaseException {

    public NotFoundException(final String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
