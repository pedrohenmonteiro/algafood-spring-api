package com.mont.algafoodapi.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.mont.algafoodapi.api.model.view.RestaurantView;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDto {
    
    @Schema(example = "1")
    @JsonView({RestaurantView.Summary.class, RestaurantView.OnlyName.class})
    private Long id;

    @Schema(example = "La Gondola")
    @JsonView({RestaurantView.Summary.class, RestaurantView.OnlyName.class})
    private String name;

    @Schema(example = "5")
    @JsonView(RestaurantView.Summary.class)
    private BigDecimal deliveryFee;

    @JsonView(RestaurantView.Summary.class)
    private CuisineDto cuisine;

    private Boolean opened;
    private Boolean active;
    private AddressDto address;
}
