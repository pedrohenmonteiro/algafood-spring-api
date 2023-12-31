package com.mont.algafoodapi.infraestructure.repository.spec;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import com.mont.algafoodapi.domain.filter.OrderFilter;
import com.mont.algafoodapi.domain.model.Order;

import jakarta.persistence.criteria.Predicate;

public class OrderSpecs {
    
    public static Specification<Order> usingFilter(OrderFilter filter) {
        return (root, query, builder) -> {

            if(Order.class.equals(query.getResultType())) {
                root.fetch("restaurant").fetch("cuisine");
                root.fetch("client");
            }

            var predicates = new ArrayList<Predicate>();

            if(filter.getClientId() != null) {
                predicates.add(builder.equal(root.get("client").get("id"), filter.getClientId()));
            }

            if(filter.getRestaurantId() != null) {
                predicates.add(builder.equal(root.get("restaurant").get("id"), filter.getRestaurantId()));
            }

            if(filter.getCreationDateInit() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("creationDate"), filter.getCreationDateInit()));
            }

            if(filter.getCreationDateEnd() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("creationDate"), filter.getCreationDateEnd()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

      
}
