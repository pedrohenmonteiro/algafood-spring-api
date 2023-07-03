package com.mont.algafoodapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mont.algafoodapi.domain.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryQueries, JpaSpecificationExecutor<Restaurant>{

    @Override
    List<Restaurant> findByNameAndFee(String name, BigDecimal minDeliveryFee, BigDecimal maxDeliveryFee);
 
}
