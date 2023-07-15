package com.mont.algafoodapi.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CuisineDto {
    
    private Long id;
    private String name;
}
