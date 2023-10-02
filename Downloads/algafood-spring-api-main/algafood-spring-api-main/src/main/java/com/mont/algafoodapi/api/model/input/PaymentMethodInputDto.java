package com.mont.algafoodapi.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodInputDto {
    
    @Schema(example = "Food Card")
    @NotBlank
    private String description;
}
