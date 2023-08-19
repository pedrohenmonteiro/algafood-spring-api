package com.mont.algafoodapi.infraestructure.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mont.algafoodapi.domain.model.ProductPhoto;
import com.mont.algafoodapi.domain.repository.ProductRepositoryQueries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public ProductPhoto save(ProductPhoto photo) {
    return em.merge(photo);
    }
    
}
