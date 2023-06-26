package com.mont.algafoodapi.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mont.algafoodapi.domain.repository.CuisineRepository;
import com.mont.algafoodapi.model.Cuisine;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class CuisineRepositoryImpl implements CuisineRepository {

       @PersistenceContext
    private EntityManager manager;

    
    @Override
    public List<Cuisine> findAll() {
        return manager.createQuery("from Cuisine", Cuisine.class)
        .getResultList();
    }

    @Override
    public Cuisine findById(Long id) {
        return manager.find(Cuisine.class, id);
    }

    @Transactional
    @Override
    public Cuisine save(Cuisine cuisine) {
        return manager.merge(cuisine);
    }

    @Transactional
    @Override
    public void delete(Cuisine cuisine) {
        cuisine = findById(cuisine.getId());
        manager.remove(cuisine);
    }
    
}
