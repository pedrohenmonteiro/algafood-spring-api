package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.exception.BadRequestException;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.State;
import com.mont.algafoodapi.domain.repository.StateRepository;

@Service
public class StateService {
    
    @Autowired
    private StateRepository stateRepository;

    public List<State> findAll() {
        return stateRepository.findAll();
    }

    public State findById(Long id) {
        return stateRepository.findById(id);
    }

    public State create(State state) {
        return stateRepository.save(state);
    }

    public State update(Long id, State state) {
        state.setId(id);
        return stateRepository.save(state);
    }

    public void delete(Long id) {

    try {
        stateRepository.delete(id);
         } catch(DataIntegrityViolationException exception) {
            throw new BadRequestException("Can not remove resource in use");
        } catch(EmptyResultDataAccessException exception) {
            throw new NotFoundException("Resource not found");
        }
    }
}
