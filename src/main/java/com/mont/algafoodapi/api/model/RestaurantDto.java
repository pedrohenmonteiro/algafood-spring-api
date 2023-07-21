package com.mont.algafoodapi.api.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    
    private Long id;
    private String name;
    private BigDecimal deliveryFee;
    private CuisineDto cuisine;
    private Boolean active;

}
