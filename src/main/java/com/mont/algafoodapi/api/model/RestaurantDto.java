package com.mont.algafoodapi.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDto {
    
    private Long id;
    private String name;
    private BigDecimal deliveryFee;
    private CuisineDto cuisine;
    private Boolean opened;
    private Boolean active;
    private AddressDto address;
}
