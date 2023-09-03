package com.mont.algafoodapi.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.mont.algafoodapi.api.model.view.RestaurantView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDto {
    
    @JsonView({RestaurantView.Summary.class, RestaurantView.OnlyName.class})
    private Long id;

    @JsonView({RestaurantView.Summary.class, RestaurantView.OnlyName.class})
    private String name;

    @JsonView(RestaurantView.Summary.class)
    private BigDecimal deliveryFee;

    @JsonView(RestaurantView.Summary.class)
    private CuisineDto cuisine;

    private Boolean opened;
    private Boolean active;
    private AddressDto address;
}
