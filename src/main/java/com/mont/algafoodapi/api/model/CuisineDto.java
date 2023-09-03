package com.mont.algafoodapi.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mont.algafoodapi.api.model.view.RestaurantView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuisineDto {
    
    @JsonView(RestaurantView.Summary.class)
    private Long id;
    
    @JsonView(RestaurantView.Summary.class)
    private String name;
}
