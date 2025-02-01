package com.farhan.blog.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private final String message;
    @Getter
    private final HttpStatus httpStatusCode;

    public ApiException(String message, HttpStatus httpStatusCode) {
        super(message);
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
