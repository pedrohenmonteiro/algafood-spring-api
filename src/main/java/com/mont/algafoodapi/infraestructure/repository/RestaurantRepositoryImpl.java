package com.mont.algafoodapi.infraestructure.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mont.algafoodapi.domain.model.Restaurant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RestaurantRepositoryImpl {
    
    @PersistenceContext
    private EntityManager em;

    public List<Restaurant> findByNameAndFee(String name, BigDecimal minDeliveryFee, BigDecimal maxDeliveryFee) {

        var jpql = "from Restaurant where name like :name "
        + "and deliveryFee between :minDeliveryFee and :maxDeliveryFee";

        return em.createQuery(jpql, Restaurant.class)
        .setParameter("name", "%" + name + "%")
        .setParameter("minDeliveryFee", minDeliveryFee)
        .setParameter("maxDeliveryFee", maxDeliveryFee)
        .getResultList();
    }

}
