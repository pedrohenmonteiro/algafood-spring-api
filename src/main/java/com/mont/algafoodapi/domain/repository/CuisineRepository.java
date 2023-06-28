package com.mont.algafoodapi.domain.repository;

import java.util.List;

import com.mont.algafoodapi.domain.model.Cuisine;

public interface CuisineRepository {
    
    List<Cuisine> findAll();
    
    Cuisine findById(Long id);

    Cuisine save(Cuisine cuisine);

    void delete(Cuisine cuisine);
}
