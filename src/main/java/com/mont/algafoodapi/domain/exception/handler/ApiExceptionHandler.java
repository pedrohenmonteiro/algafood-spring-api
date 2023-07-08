package com.mont.algafoodapi.domain.exception.handler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.aspectj.weaver.ast.Instanceof;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
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

    @Override
    public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
        Object[] args = {ex.getPropertyName(), ex.getValue()};
		String defaultDetail = "Failed to convert '" + args[0] + "' with value: '" + args[1] + "'";
		String messageCode = ErrorResponse.getDefaultDetailMessageCode(TypeMismatchException.class, null);
		ProblemDetail body = createProblemDetail(ex, status, defaultDetail, messageCode, args, req);
        return handleExceptionInternal(ex, null, new HttpHeaders(), status, req);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
                
        Throwable rootCause = ex.getCause();        
        if (rootCause instanceof UnrecognizedPropertyException) {
         return handleUnrecognizedPropertyException((UnrecognizedPropertyException)rootCause, status, req);
    } 
		return handleExceptionInternal(ex, null, headers, status, req);
	}
    
    private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex, HttpStatusCode status, WebRequest req) {
        String propertyName = ex.getPath().get(0).getFieldName();
        String errorMessage = "The property "+propertyName+" does not exist. Correct or remove this property and try again.";
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, req);

    }


 

    // default implementation for ResponseEntityExceptionHandler
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest req) {

        String title = HttpStatus.valueOf(statusCode.value()).getReasonPhrase();      

        var newBody = ExceptionResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(statusCode.value())
            .title(title)
            .message(ex.getMessage())
            .details(req.getDescription(false));

        if(body == null) {
            body = newBody.build();
        } else if(body instanceof String) {
            String message = (String) body;
            newBody.message(message);
            body = newBody.build();
        }
        
        System.out.println(ex.getClass().getName());
        System.out.println(ex.getCause());

        return super.handleExceptionInternal(ex, body, headers, statusCode, req);
    }
}

