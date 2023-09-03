package com.mont.algafoodapi.api.mapper;

import java.util.List;

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

    public List<StateDto> toCollectionDto(List<State> state) {
        return state.stream().map(this::fromEntityToDto).toList();
    }

    public void copyToDomainObject(StateInputDto stateInputDto, State state) {
      modelMapper.map(stateInputDto, state);
    }
}
