package com.mont.algafoodapi.api.model.input;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInputDto {
    
    @Valid
    @NotNull
    private RestaurantIdInputDto restaurant;

    @Valid
    @NotNull
    private PaymentMethodIdInputDto paymentMethod;

    @Valid
    @NotNull
    private AddressInputDto addressDelivery;

    @NotNull
    @Valid
    @Size(min = 1)
    private List<OrderItemInputDto> items;

}
