package com.mont.algafoodapi.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CityInputDto {
    
    @NotBlank
    private String name;
    
    @NotNull
    private Long stateId;

    
}
