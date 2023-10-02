package com.mont.algafoodapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.mont.algafoodapi.domain.model.PaymentMethod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    
    private String code;
    private BigDecimal subtotal;
    private BigDecimal totalValue;
    private BigDecimal deliveryFee;
    private String status;
    private OffsetDateTime creationDate;
    private OffsetDateTime cancelDate;
    private OffsetDateTime confirmDate;
    private OffsetDateTime deliveredDate;
    private RestaurantSummaryDto restaurant;
    private UserDto client;
    private PaymentMethod paymentMethod;
    private AddressDto address;
    private List<OrderItemDto> items;
}