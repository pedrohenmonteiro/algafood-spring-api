package com.mont.algafoodapi.api.model.input;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInputDto {

    @Schema(example = "Pizza Pepperoni")
    @NotNull
    private String name;

    @Schema(example = "Tomato sauce, mozzarella, and pepperoni")
    @NotNull
    private String description;

    @Schema(example = "35")
    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @Schema(example = "true")
    @NotNull
    private Boolean active;
}
