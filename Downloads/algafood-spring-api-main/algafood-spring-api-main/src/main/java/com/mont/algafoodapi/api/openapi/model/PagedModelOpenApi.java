package com.mont.algafoodapi.api.openapi.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedModelOpenApi<T> {
    List<T> content;

    @Schema(example = "10")
    private Long size;

    @Schema(example = "50")
    private Long totalElements;

    @Schema(example = "5")
    private Long totalPage;

    @Schema(example = "0")
    private Long number;
}
