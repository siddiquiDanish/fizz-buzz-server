package com.fizzbuzz.fizzbuzzserver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Map<String, Object>> handleParameterExceptions(Exception ex) {
        Map<String, Object> error = new HashMap<>();

        if (ex instanceof MissingServletRequestParameterException missingParamEx) {
            error.put("error", "Missing parameter: " + missingParamEx.getParameterName());
        } else if (ex instanceof MethodArgumentTypeMismatchException invalidTypeEx) {
            error.put("error", "Invalid parameter : " + invalidTypeEx.getName() + ". Expected type: " + invalidTypeEx.getRequiredType());
        } else {
            error.put("error", "Unexpected error occurred" + ex.getMessage());
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Unexpected error occurred"+ ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}