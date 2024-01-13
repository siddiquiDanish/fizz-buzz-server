package com.fizzbuzz.fizzbuzzserver;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    public void testHandleMissingParameterException() {
        MissingServletRequestParameterException exception = new MissingServletRequestParameterException("paramName", "paramType");
        ResponseEntity<Map<String, Object>> responseEntity = globalExceptionHandler.handleParameterExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Missing parameter: paramName", responseEntity.getBody().get("error"));
    }

    @Test
    public void testHandleMethodArgumentTypeMismatchException() {
        MethodArgumentTypeMismatchException exception = new MethodArgumentTypeMismatchException("value", String.class, "paramName", null, null);
        ResponseEntity<Map<String, Object>> responseEntity = globalExceptionHandler.handleParameterExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid parameter : paramName. Expected type: class java.lang.String", responseEntity.getBody().get("error"));
    }

    @Test
    public void testHandleGenericException() {
        Exception ex = new RuntimeException("Test unexpected error");

        ResponseEntity<Map<String, Object>> responseEntity = globalExceptionHandler.handleGenericException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Unexpected error occurred" + ex.getMessage(), responseEntity.getBody().get("error"));
    }
}
