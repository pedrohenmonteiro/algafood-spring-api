package com.mont.algafoodapi.api.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    
    @Schema(example = "1")
    private Long id;

    @Schema(example = "1")
    private Integer quantity;

    @Schema(example = "79.00")
    private BigDecimal totalPrice;

    @Schema(example = "79.00")
    private BigDecimal unitPrice;

    @Schema(example = "Ao ponto")
    private String observation;

    @Schema(example = "Layers of pasta, meat sauce, and cheese")
    private ProductDto product;

}
