package com.mont.algafoodapi.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mont.algafoodapi.api.model.view.RestaurantView;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuisineDto {
    
    @JsonView(RestaurantView.Summary.class)
    @Schema(example = "1")
    private Long id;
    
    @JsonView(RestaurantView.Summary.class)
    @Schema(example = "Chinese")
    private String name;
}
