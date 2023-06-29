package com.mont.algafoodapi.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mont.algafoodapi.domain.model.City;
import com.mont.algafoodapi.domain.repository.CityRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class CityRepositoryImpl implements CityRepository {
    
      @PersistenceContext
    private EntityManager manager;

    
    @Override
    public List<City> findAll() {
        return manager.createQuery("from City", City.class)
        .getResultList();
    }

    @Override
    public City findById(Long id) {
        return manager.find(City.class, id);
    }

    @Transactional
    @Override
    public City save(City city) {
        return manager.merge(city);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        var city = findById(id);
        manager.remove(city);
    }
}
