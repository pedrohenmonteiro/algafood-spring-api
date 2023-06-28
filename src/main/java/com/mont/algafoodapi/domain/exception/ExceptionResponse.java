package com.mont.algafoodapi.domain.exception;

import java.time.Instant;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private Instant timestamp;
    private String message;
    private String details;
    private int status;
    
}
