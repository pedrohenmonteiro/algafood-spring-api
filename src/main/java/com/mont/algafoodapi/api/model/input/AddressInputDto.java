package com.mont.algafoodapi.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInputDto {

    @Schema(example = "38400-200")
    @NotBlank
    private String zipcode;

    @Schema(example = "Rua Floriano Peixoto")
    @NotBlank
    private String street;

    @Schema(example = "930")
    @NotBlank
    private String number;

    @Schema(example = "Apto 801")
    private String complement;

    @Schema(example = "Neves Souza")
    @NotBlank
    private String neighbourhood;

    @Valid
    @NotNull
    private CityIdInputDto city;

}
