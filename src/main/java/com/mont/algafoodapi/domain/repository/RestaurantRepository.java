package com.mont.algafoodapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mont.algafoodapi.domain.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

    List<Restaurant> findByNameAndFee(String name, BigDecimal minDeliveryFee, BigDecimal maxDeliveryFee);
 
}
