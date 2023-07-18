package com.mont.algafoodapi.api.model.input;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantInputDto {

    @NotBlank
    private String name;
    
    @PositiveOrZero
    @NotNull
    private BigDecimal deliveryFee;

    @Valid
    @NotNull
    @JsonProperty("cuisine")
    private CuisineIdInputDto cuisineId;
}
