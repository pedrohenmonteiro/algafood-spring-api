package com.mont.algafoodapi.domain.repository;

import java.util.List;

import com.mont.algafoodapi.domain.model.State;

public interface StateRepository {
    List<State> findAll();
    
    State findById(Long id);

    State save(State state);

    void delete(Long id);
}
