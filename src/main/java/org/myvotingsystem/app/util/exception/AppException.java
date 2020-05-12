package org.myvotingsystem.app.util.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
    private final String msgCode;
    private final HttpStatus httpStatus;
    private final String[] args;


    public AppException(String msgCode, HttpStatus httpStatus, String... args) {
        this.msgCode = msgCode;
        this.httpStatus = httpStatus;
        this.args = args;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String[] getArgs() {
        return args;
    }
}
