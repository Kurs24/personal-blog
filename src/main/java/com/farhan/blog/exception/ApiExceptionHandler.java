package com.farhan.blog.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import java.util.ArrayList;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionResponse> handler(MethodArgumentNotValidException ex) {
        ApiExceptionResponse response = ApiExceptionResponse.builder().build();
        ArrayList<String> errors = new ArrayList<>();
        ex.getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        response.setErrorMessages(errors);
        response.setTimestamp(Instant.now().getEpochSecond());
        response.setStatusCode(ex.getStatusCode().toString());
        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiExceptionResponse> handler(SQLIntegrityConstraintViolationException ex) {
        ApiExceptionResponse response = ApiExceptionResponse.builder().build();
        response.setErrorMessages(Collections.singletonList(ex.getMessage()));
        response.setTimestamp(Instant.now().getEpochSecond());
        response.setStatusCode("400");
        return ResponseEntity.status(400).body(response);
    }
}
