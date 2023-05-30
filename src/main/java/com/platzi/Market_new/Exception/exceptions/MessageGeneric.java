package com.platzi.Market_new.Exception.exceptions;

import org.springframework.http.HttpStatus;

public class MessageGeneric extends RuntimeException{

    private String code;
    private HttpStatus status;

    public MessageGeneric(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
