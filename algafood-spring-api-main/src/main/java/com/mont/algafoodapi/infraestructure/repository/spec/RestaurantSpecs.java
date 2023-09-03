package com.mont.algafoodapi.infraestructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.mont.algafoodapi.domain.model.Restaurant;

public class RestaurantSpecs {
    
    public static Specification<Restaurant> findZeroDeliveryFee() {
        return (root, query, builder) -> builder.equal(root.get("deliveryFee"), BigDecimal.ZERO);
    }

        public static Specification<Restaurant> findWithSimilarName(String name) {
            return (root, query, builder) -> builder.like(root.get("name"), "%"+ name +"%");

}
}