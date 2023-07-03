package com.mont.algafoodapi.infraestructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.mont.algafoodapi.domain.model.Restaurant;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class RestaurantZeroDeliveryFee implements Specification<Restaurant>{

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        // TODO Auto-generated method stub
        return builder.equal(root.get("deliveryFee"), BigDecimal.ZERO);
    }
    
}
