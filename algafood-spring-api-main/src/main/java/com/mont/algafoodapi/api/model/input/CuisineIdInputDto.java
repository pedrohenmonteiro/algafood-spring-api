package com.mont.algafoodapi.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CuisineIdInputDto {
    
    @NotNull
    private Long id;
}
