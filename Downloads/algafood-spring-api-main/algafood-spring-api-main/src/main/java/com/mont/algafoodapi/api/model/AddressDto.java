package com.mont.algafoodapi.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
        
    @Schema(example = "38400-200")
    private String zipcode;

    @Schema(example = "Rua Floriano Peixoto")
    private String street;

    @Schema(example = "930")
    private String number;

    @Schema(example = "Apto 801")
    private String complement;

    @Schema(example = "Neves Souza")
    private String neighbourhood;

    private CitySummaryDto city;
}
