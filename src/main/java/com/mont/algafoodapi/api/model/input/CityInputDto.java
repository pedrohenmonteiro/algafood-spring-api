package com.mont.algafoodapi.api.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CityInputDto {
    
    @NotBlank
    private String name;
    
     @JsonProperty("state")
    @Valid
    @NotNull
    private StateIdInputDto stateId;
}
