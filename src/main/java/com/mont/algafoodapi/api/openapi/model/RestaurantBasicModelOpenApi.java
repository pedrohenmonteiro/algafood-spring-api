package com.mont.algafoodapi.api.openapi.model;

import java.math.BigDecimal;

import com.mont.algafoodapi.api.model.CuisineDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantBasicModelOpenApi {
    
    @Schema(example = "1")
    private Long id;

    @Schema(example = "La Gondola")
    private String name;

    @Schema(example = "5")
    private BigDecimal deliveryFee;

    private CuisineDto cuisine;
}
