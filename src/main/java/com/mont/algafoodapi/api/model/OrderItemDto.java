package com.mont.algafoodapi.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    
    private Long id;
    private Integer quantity;
    private BigDecimal totalPrice;
    private BigDecimal unitPrice;
    private String observation;
    private ProductDto product;

}
