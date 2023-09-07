package com.mont.algafoodapi.api.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    @Schema(example = "1")
    private Long id;

    @Schema(example = "Pizza Pepperoni")
    private String name;

    @Schema(example = "Tomato sauce, mozzarella, and pepperoni")
    private String description;

    @Schema(example = "35")
    private BigDecimal price;

    @Schema(example = "true")
    private Boolean active;
}
