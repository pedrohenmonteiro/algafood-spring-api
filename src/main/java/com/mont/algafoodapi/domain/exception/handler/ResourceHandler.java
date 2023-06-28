package com.mont.algafoodapi.domain.exception.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.mont.algafoodapi.domain.exception.ExceptionResponse;
import com.mont.algafoodapi.domain.exception.NotFoundException;

@RestControllerAdvice
public class ResourceHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundExceptionHandler(Exception ex, WebRequest req)
    {
        var exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage(), req.getDescription(false), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
}
