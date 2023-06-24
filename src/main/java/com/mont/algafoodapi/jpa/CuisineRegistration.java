package com.mont.algafoodapi.jpa;


import java.util.List;

import org.springframework.stereotype.Component;

import com.mont.algafoodapi.model.Cuisine;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class CuisineRegistration {
    
    @PersistenceContext
    private EntityManager manager;

    public List<Cuisine> list() {
        return manager.createQuery("from Cuisine", Cuisine.class)
        .getResultList();
    }

    public Cuisine findById(Long id) {
        return manager.find(Cuisine.class, id);
    }

    @Transactional
    public Cuisine save(Cuisine cuisine) {
        return manager.merge(cuisine);
    }


}
