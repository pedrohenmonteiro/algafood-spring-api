package com.mont.algafoodapi.domain.repository;

import java.util.List;

import com.mont.algafoodapi.domain.model.Restaurant;

public interface RestaurantRepository {
    List<Restaurant> findAll();
    
    Restaurant findById(Long id);

    Restaurant save(Restaurant restaurant);

    void delete(Long id);
}
