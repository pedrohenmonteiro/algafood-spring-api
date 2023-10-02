package com.mont.algafoodapi.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemInputDto {
    
    @Schema(example = "5")
    @NotNull
    private Long productId;

    @Schema(example = "1")
    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @Schema(example = "Ao ponto")
    private String observation;

}
