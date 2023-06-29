package com.mont.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mont.algafoodapi.domain.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
 
}
