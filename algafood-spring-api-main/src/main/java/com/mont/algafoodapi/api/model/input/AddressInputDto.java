package com.mont.algafoodapi.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInputDto {

    @NotBlank
    private String zipcode;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    private String complement;

    @NotBlank
    private String neighbourhood;

    @Valid
    @NotNull
    private CityIdInputDto city;
}
