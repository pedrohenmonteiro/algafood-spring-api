package com.mont.algafoodapi.domain.exception.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
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
        Object[] args = {ex.getPropertyName(),ex.getValue(), ex.getRequiredType().getSimpleName()};
		String errorMessage = String.format("The URL param '%s' received the value '%s' which is a invalid type. The value type must be %s.", args);
        
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, req);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
        String errorMessage = "The request body is invalid. Check syntax error";

                
        Throwable rootCause = ex.getCause();        
        if (rootCause instanceof PropertyBindingException) {
         return handlePropertyBindingException((PropertyBindingException)rootCause, status, req);
    } 
        if(rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, status, req);
        }


		return handleExceptionInternal(ex, errorMessage, headers, status, req);
	}
    
    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpStatusCode status, WebRequest req) {
        String path = joinPath(ex.getPath());
        String errorMessage = "The property '"+path+"' does not exist. Correct or remove this property and try again.";
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, req);

    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpStatusCode status, WebRequest req) {
        String path = joinPath(ex.getPath());
        Object[] args = {ex.getValue(), path, ex.getTargetType().getSimpleName()};
        String errorMessage = String.format("The value %s from %s has a invalid type. The value type must be %s", args);

        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, req);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaughtExceptions(Exception ex, WebRequest req)
    {
        String errorMessage = "An unexpected internal system error has occurred."
        + "Try again later and if the problem persists contact the system administrator.";
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, req);

    }

    private String joinPath(List<Reference> references) {
        List<String> paths = new ArrayList<>();
        
        for(int i = 0; i < references.size(); i++) {
           paths.add(references.get(i).getFieldName());
        }
        return String.join(".", paths);

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
        
        // System.out.println(ex.getClass().getName());
        // System.out.println(ex.getCause());

        return super.handleExceptionInternal(ex, body, headers, statusCode, req);
    }
}

