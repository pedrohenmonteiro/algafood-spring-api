package com.mont.algafoodapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.mont.algafoodapi.domain.model.OrderStatus;
import com.mont.algafoodapi.domain.model.PaymentMethod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    
    private Long id;
    private BigDecimal subtotal;
    private BigDecimal totalValue;
    private BigDecimal deliveryFee;
    private OrderStatus status;
    private OffsetDateTime creationDate;
    private OffsetDateTime cancelDate;
    private OffsetDateTime confirmDate;
    private OffsetDateTime deliveredDate;
    private AddressDto address;
    private UserDto client;
    private RestaurantSummaryDto restaurant;
    private PaymentMethod paymentMethod;
    private List<OrderItemDto> items;
}
