package com.mont.algafoodapi.domain.exception;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Builder
@Getter
public class ExceptionResponse {


    //"2023-09-02T04:48:51.195430085"
    //"2023-09-02T04:49:45.408085817"

    @Schema(example = "2023-09-02T04:49:45.408085817")
    private OffsetDateTime timestamp;

    @Schema(example = "400")
    private Integer status;

    @Schema(example = "Bad Request")
    private String title;

    @Schema(example = "One or more fields are invalid. Fill in correctly and try again.")
    private String message;

    @Schema(example = "uri=/cities")
    private String details;
    private List<Field> fields;
    

    @Getter
    @Builder
    public static class Field {

        @Schema(example = "name")
        private String name;
             
        @Schema(example = "name is required")
        private String userMessage;
    }
}
