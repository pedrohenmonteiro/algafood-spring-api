package com.mont.algafoodapi.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateInputDto {
    
    @NotBlank
    private String name;
}
