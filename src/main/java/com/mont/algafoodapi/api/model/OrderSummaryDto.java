package com.mont.algafoodapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.mont.algafoodapi.domain.model.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSummaryDto {
    

    private Long id;
    private BigDecimal subtotal;
    private BigDecimal totalValue;
    private BigDecimal deliveryFee;
    private OrderStatus status;
    private OffsetDateTime creationDate;
    private UserDto client;
    private RestaurantSummaryDto restaurant;
}
