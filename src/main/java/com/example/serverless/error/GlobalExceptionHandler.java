package com.example.serverless.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(UserNotFoundException resourceNotFound) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(resourceNotFound.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

