package com.mont.algafoodapi.api.model.input;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInputDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    @PositiveOrZero
    private BigDecimal price;
    @NotNull
    private Boolean active;
}
