package com.jgr.api.clients.controllers;

import com.jgr.api.clients.exceptions.ApiError;
import com.jgr.api.clients.exceptions.ClientDataAccessException;
import com.jgr.api.clients.exceptions.ClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = ClientException.class)
    public ResponseEntity<ApiError> requestExceptionHandler(ClientException exception, HttpServletRequest request) {
        ApiError apiError = new ApiError();
        apiError.setTimestamp(Instant.now());
        apiError.setCode(exception.getHttpStatus().value());
        apiError.setMessageCode(exception.getHttpStatus().getReasonPhrase());
        apiError.setMessage(exception.getMessage());
        apiError.setPath(request.getRequestURI());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ClientDataAccessException.class)
    public ResponseEntity<ApiError> dataExceptionHandler(ClientDataAccessException exception, HttpServletRequest request) {
        ApiError apiError = new ApiError();
        apiError.setTimestamp(Instant.now());
        apiError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiError.setMessageCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        apiError.setMessage(exception.getMessage());
        apiError.setPath(request.getRequestURI());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
