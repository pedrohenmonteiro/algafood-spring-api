package com.mont.algafoodapi.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.StateDto;
import com.mont.algafoodapi.api.model.input.StateInputDto;
import com.mont.algafoodapi.domain.model.State;

@Component
public class StateMapper {
    
    @Autowired
    private ModelMapper modelMapper;
    

    public State fromDtoToEntity(StateInputDto stateInputDto) {
        return modelMapper.map(stateInputDto, State.class);
    }

    public StateDto fromEntityToDto(State state) {
        return modelMapper.map(state, StateDto.class);
    }
}
