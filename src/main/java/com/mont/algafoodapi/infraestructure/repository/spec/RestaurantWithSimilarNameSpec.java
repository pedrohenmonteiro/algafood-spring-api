package com.mont.algafoodapi.infraestructure.repository.spec;

import org.springframework.data.jpa.domain.Specification;

import com.mont.algafoodapi.domain.model.Restaurant;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestaurantWithSimilarNameSpec implements Specification<Restaurant>{

    private String name;

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        return builder.like(root.get("name"), "%"+name+"%");
    }
    
}
