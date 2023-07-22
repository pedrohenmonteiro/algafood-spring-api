package com.mont.algafoodapi.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitySummaryDto {
    private Long id;
    private String name;
    @JsonProperty("state")
    private String stateName;
}
