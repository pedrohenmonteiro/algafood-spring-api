package com.mont.algafoodapi.api.model.input;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInputDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;
}
