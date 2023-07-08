package com.mont.algafoodapi.domain.exception;

import java.time.LocalDateTime;


import lombok.Builder;
import lombok.Getter;

// @JsonInclude(Include.NON_NULL)
@Builder
@Getter
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private Integer status;
    private String title;
    private String message;
    private String details;
    
}
