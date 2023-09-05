package com.mont.algafoodapi.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    
    @Schema(example = "3")
    private Long id;

    @Schema(example = "José Souza")
    private String name;

    @Schema(example = "jose.aux@algafood.com")
    private String email;
}
