package com.mont.algafoodapi.domain.exception.handler;

import java.time.Instant;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mont.algafoodapi.domain.exception.BadRequestException;
import com.mont.algafoodapi.domain.exception.ConflictException;
import com.mont.algafoodapi.domain.exception.ExceptionResponse;
import com.mont.algafoodapi.domain.exception.NotFoundException;

@RestControllerAdvice
public class ResourceHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundExceptionHandler(Exception ex, WebRequest req)
    {
        var exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage(), req.getDescription(false), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> badRequestExceptionHandler(Exception ex, WebRequest req)
    {
        var exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage(), req.getDescription(false), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(ConflictException.class) 
        public ResponseEntity<ExceptionResponse> conflictExceptionHandler(Exception ex, WebRequest req)
    {
        var exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage(), req.getDescription(false), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }


    // default implementation for ResponseEntityExceptionHandler
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest req) {

        body = new ExceptionResponse(Instant.now(), ex.getMessage(), req.getDescription(false), statusCode.value());

        return super.handleExceptionInternal(ex, body, headers, statusCode, req);
    }
}

