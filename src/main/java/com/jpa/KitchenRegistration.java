package com.jpa;


import java.util.List;

import com.mont.algafoodapi.model.Kitchen;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class KitchenRegistration {
    
    @PersistenceContext
    private EntityManager manager;

    public List<Kitchen> list() {
        return manager.createQuery("from Kitchen", Kitchen.class)
        .getResultList();
    }
}
