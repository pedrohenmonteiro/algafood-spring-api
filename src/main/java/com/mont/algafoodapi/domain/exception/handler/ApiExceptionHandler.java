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
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundExceptionHandler(Exception ex, WebRequest req)
    {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NOT_FOUND, req);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestExceptionHandler(Exception ex, WebRequest req)
    {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.BAD_REQUEST, req);

    }

    @ExceptionHandler(ConflictException.class) 
        public ResponseEntity<?> conflictExceptionHandler(Exception ex, WebRequest req)
    {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.CONFLICT, req);

    }


    // default implementation for ResponseEntityExceptionHandler
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest req) {

        if(body == null) {
            body = new ExceptionResponse(Instant.now(), ex.getMessage(), req.getDescription(false), statusCode.value());
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, req);
    }
}

