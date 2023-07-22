package com.mont.algafoodapi.api.model.input;

import jakarta.validation.constraints.NotBlank;

public class UserInputDto {
    
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
