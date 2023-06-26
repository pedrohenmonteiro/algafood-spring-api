package com.mont.algafoodapi.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mont.algafoodapi.domain.repository.RestaurantRepository;
import com.mont.algafoodapi.model.Cuisine;
import com.mont.algafoodapi.model.Restaurant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {
    
      @PersistenceContext
    private EntityManager manager;

    
    @Override
    public List<Restaurant> findAll() {
        return manager.createQuery("from Restaurant", Restaurant.class)
        .getResultList();
    }

    @Override
    public Restaurant findById(Long id) {
        return manager.find(Restaurant.class, id);
    }

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        return manager.merge(restaurant);
    }

    @Transactional
    @Override
    public void delete(Restaurant restaurant) {
        restaurant = findById(restaurant.getId());
        manager.remove(restaurant);
    }
}
