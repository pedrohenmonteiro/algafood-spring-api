package com.mont.algafoodapi.api.model;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestaurantDto {
    
    private Long id;
    @NotBlank
    private String name;

    @PositiveOrZero
    @NotNull
    private BigDecimal deliveryFee;

    @Valid
    @NotNull
    private CuisineDto cuisine;

}
