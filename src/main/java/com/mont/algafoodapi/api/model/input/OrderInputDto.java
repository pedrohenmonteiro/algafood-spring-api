package com.mont.algafoodapi.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
    private PaymentMethodInputDto paymentMethod;

    @Valid
    @NotNull
    private AddressInputDto address;

}
