package com.mont.algafoodapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.mont.algafoodapi.domain.model.Restaurant;

public interface RestaurantRepositoryQueries {

    List<Restaurant> findByNameAndFee(String name, BigDecimal minDeliveryFee, BigDecimal maxDeliveryFee);

}