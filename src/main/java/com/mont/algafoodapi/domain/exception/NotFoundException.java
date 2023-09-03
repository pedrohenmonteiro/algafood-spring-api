package com.mont.algafoodapi.domain.exception;

public class NotFoundException extends RuntimeException {
    
    public NotFoundException(String msg) {
        super(msg);
    }
}
