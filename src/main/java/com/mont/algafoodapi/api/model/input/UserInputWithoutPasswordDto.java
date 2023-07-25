package com.mont.algafoodapi.api.model.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserInputWithoutPasswordDto {
     
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

}
