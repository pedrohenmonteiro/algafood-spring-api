package com.mont.algafoodapi.infraestructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mont.algafoodapi.domain.model.Restaurant;
import com.mont.algafoodapi.domain.repository.RestaurantRepositoryQueries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Restaurant> findByNameAndFee(String name, BigDecimal minDeliveryFee, BigDecimal maxDeliveryFee) {

        var jpql = new StringBuilder()
        .append("from Restaurant where 0 = 0 ");

        var parameters = new HashMap<String, Object>();

        if (StringUtils.hasLength(name)) {
            jpql.append("and name like :name ");
            parameters.put("name", "%" + name + "%");
        }

        if (minDeliveryFee != null) {
            jpql.append("and deliveryFee >= :minDeliveryFee ");
            parameters.put("minDeliveryFee", minDeliveryFee);            
        }

         if (maxDeliveryFee != null) {
            jpql.append("and deliveryFee <= :maxDeliveryFee ");
            parameters.put("maxDeliveryFee", maxDeliveryFee);
        }

        var query = em.createQuery(jpql.toString(), Restaurant.class);
        
        parameters.forEach((k, v) -> {
             query.setParameter(k,  v);
        });

        return query.getResultList();
        
    }

}
