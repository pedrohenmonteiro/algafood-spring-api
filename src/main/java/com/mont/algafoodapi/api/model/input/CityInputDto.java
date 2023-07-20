package com.mont.algafoodapi.api.model.input;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityInputDto {
    
    @NotBlank
    private String name;
    
    @Valid
    @NotNull
    private StateIdInputDto state;
}
