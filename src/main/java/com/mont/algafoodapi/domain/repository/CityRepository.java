package com.mont.algafoodapi.domain.repository;

import java.util.List;

import com.mont.algafoodapi.domain.model.City;

public interface CityRepository {
    List<City> findAll();
    
    City findById(Long id);

    City save(City state);

    void delete(Long id);
}
