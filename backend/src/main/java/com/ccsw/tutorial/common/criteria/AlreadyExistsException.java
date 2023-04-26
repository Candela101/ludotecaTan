package com.ccsw.tutorial.common.criteria;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends Exception{
    
    private final HttpStatus status;

    public AlreadyExistsException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
