package com.mont.algafoodapi.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodDto {
    
    @Schema(example = "1")
    private Long id;

    @Schema(example = "Credit Card")
    private String description;
}
