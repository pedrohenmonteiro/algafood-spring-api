package com.mont.algafoodapi.api.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateInputDto {
    
    @NotBlank
    private String name;

    @JsonProperty("state")
    @Valid
    @NotNull
    private StateIdInputDto stateId;
}
