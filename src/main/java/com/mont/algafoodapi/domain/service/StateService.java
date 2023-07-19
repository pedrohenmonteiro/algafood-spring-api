package com.mont.algafoodapi.domain.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.StateMapper;
import com.mont.algafoodapi.api.model.StateDto;
import com.mont.algafoodapi.api.model.input.StateInputDto;
import com.mont.algafoodapi.domain.exception.BadRequestException;
import com.mont.algafoodapi.domain.exception.ConflictException;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.State;
import com.mont.algafoodapi.domain.repository.StateRepository;

@Service
public class StateService {
    
    @Autowired 
    private StateRepository stateRepository;

    @Autowired
    private StateMapper stateMapper;

    public List<StateDto> findAll() {
        return stateMapper.toCollectionDto(stateRepository.findAll());
        
    }

    public StateDto findById(Long id) {
        return stateMapper.fromEntityToDto(getState(id));
    }
    
    public StateDto create(StateInputDto stateInputDto) {
       
       var state = stateMapper.fromDtoToEntity(stateInputDto);
        
        return stateMapper.fromEntityToDto(stateRepository.save(state));
    }

    public StateDto update(Long id, StateInputDto stateInputDto) {
        var state = getState(id);
        stateMapper.copyToDomainObject(stateInputDto, state);
        return stateMapper.fromEntityToDto(stateRepository.save(state));
    }

    public void delete(Long id) {
        try {
            getState(id);
            stateRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Cannot delete resource due to existing references");
        }
        
    }

    private State getState(Long id) {
        return stateRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found"));
    }
}
