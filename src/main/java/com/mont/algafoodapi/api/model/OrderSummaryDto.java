package com.mont.algafoodapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSummaryDto {
    

    private String code;
    private BigDecimal subtotal;
    private BigDecimal totalValue;
    private BigDecimal deliveryFee;
    private String status;
    private OffsetDateTime creationDate;
    // private UserDto client;
    private String nameClient;
    private RestaurantSummaryDto restaurant;
}
