package com.mont.algafoodapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    
    @Schema(example = "53c2da0b-63e9-4dd4-81a9-2ae03523d160")
    private String code;

    @Schema(example = "79.00")
    private BigDecimal subtotal;

    @Schema(example = "82.00")
    private BigDecimal totalValue;

    @Schema(example = "3.00")
    private BigDecimal deliveryFee;

    @Schema(example = "DELIVERED")
    private String status;

    @Schema(example = "2019-11-02T21:00:30Z")
    private OffsetDateTime creationDate;

    @Schema(example = "null")
    private OffsetDateTime cancelDate;

    @Schema(example = "2019-11-02T21:01:21Z")
    private OffsetDateTime confirmDate;

    @Schema(example = "2019-11-02T21:20:10Z")
    private OffsetDateTime deliveredDate;

    
    private RestaurantSummaryDto restaurant;
    private UserDto client;
    private PaymentMethodDto paymentMethod;
    private AddressDto address;
    private List<OrderItemDto> items;
}
