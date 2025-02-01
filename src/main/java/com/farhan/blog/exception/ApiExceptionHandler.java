package com.farhan.blog.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Collections;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponse> handler(ApiException ex) {
        ApiExceptionResponse response = ApiExceptionResponse.builder().build();
        response.setErrorMessages(Collections.singletonList(ex.getMessage()));
        response.setTimestamp(Instant.now().getEpochSecond());
        response.setStatusCode(ex.getHttpStatusCode().name());
        return ResponseEntity.status(ex.getHttpStatusCode()).body(response);
    }
}
