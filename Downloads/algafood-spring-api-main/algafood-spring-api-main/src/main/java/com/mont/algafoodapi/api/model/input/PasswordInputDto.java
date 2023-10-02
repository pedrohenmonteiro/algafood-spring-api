package com.mont.algafoodapi.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordInputDto {
    
    @Schema(example = "123", type = "string")
    private String currentPassword;

	@Schema(example = "123", type = "string")
    private String newPassword;
}
