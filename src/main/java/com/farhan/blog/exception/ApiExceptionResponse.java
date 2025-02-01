package com.farhan.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiExceptionResponse {
    private String statusCode;
    private List<String> errorMessages;
    private Long timestamp;
}
