package com.mont.algafoodapi.jpa;


import java.util.List;

import org.springframework.stereotype.Component;

import com.mont.algafoodapi.model.Kitchen;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class KitchenRegistration {
    
    @PersistenceContext
    private EntityManager manager;

    public List<Kitchen> list() {
        return manager.createQuery("from Kitchen", Kitchen.class)
        .getResultList();
    }

    @Transactional
    public Kitchen create(Kitchen kitchen) {
        return manager.merge(kitchen);
    }
}
