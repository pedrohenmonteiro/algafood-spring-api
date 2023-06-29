package com.mont.algafoodapi.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mont.algafoodapi.domain.model.State;
import com.mont.algafoodapi.domain.repository.StateRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class StateRepositoryImpl implements StateRepository {
    
      @PersistenceContext
    private EntityManager manager;

    
    @Override
    public List<State> findAll() {
        return manager.createQuery("from State", State.class)
        .getResultList();
    }

    @Override
    public State findById(Long id) {
        return manager.find(State.class, id);
    }

    @Transactional
    @Override
    public State save(State state) {
        return manager.merge(state);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        var state = findById(id);
        manager.remove(state);
    }
}
