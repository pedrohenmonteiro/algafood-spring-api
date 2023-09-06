package com.mont.algafoodapi.infraestructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mont.algafoodapi.domain.model.Restaurant;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;
import com.mont.algafoodapi.domain.repository.RestaurantRepositoryQueries;
import com.mont.algafoodapi.infraestructure.repository.spec.RestaurantSpecs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {
    
    @PersistenceContext
    private EntityManager em;

    @Autowired @Lazy
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findByNameAndFee(String name, BigDecimal minDeliveryFee, BigDecimal maxDeliveryFee) {

        

        // var jpql = new StringBuilder()
        // .append("from Restaurant where 0 = 0 ");

        // var parameters = new HashMap<String, Object>();

        // if (StringUtils.hasLength(name)) {
        //     jpql.append("and name like :name ");
        //     parameters.put("name", "%" + name + "%");
        // }

        // if (minDeliveryFee != null) {
        //     jpql.append("and deliveryFee >= :minDeliveryFee ");
        //     parameters.put("minDeliveryFee", minDeliveryFee);            
        // }

        //  if (maxDeliveryFee != null) {
        //     jpql.append("and deliveryFee <= :maxDeliveryFee ");
        //     parameters.put("maxDeliveryFee", maxDeliveryFee);
        // }

        // var query = em.createQuery(jpql.toString(), Restaurant.class);
        
        // parameters.forEach((k, v) -> {
        //      query.setParameter(k,  v);
        // });


        
        CriteriaBuilder builder = em.getCriteriaBuilder();    

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);

        // from Restaurant
        Root<Restaurant> root = criteria.from(Restaurant.class);  

        var predicates = new ArrayList<Predicate>();

        if(StringUtils.hasText(name)) {
            Predicate namePredicate = builder.like(root.get("name"), "%" + name + "%"); 
            predicates.add(namePredicate);
        }

        if(minDeliveryFee != null) {
        Predicate minFeePredicate = builder.greaterThanOrEqualTo(root.get("deliveryFee"), minDeliveryFee);
        predicates.add(minFeePredicate);

        }

        if(maxDeliveryFee != null) {
        Predicate maxFeePredicate = builder.lessThanOrEqualTo(root.get("deliveryFee"), maxDeliveryFee);
        predicates.add(maxFeePredicate);
        }

        // where name like :name and deliveryFee >= :minDeliveryFee and deliveryFee <= :maxDeliveryFee
        criteria.where(predicates.toArray(new Predicate[0])); 

        return em.createQuery(criteria).getResultList();
        
     }

    @Override
    public List<Restaurant> findByZeroDeliveryFee(String name) {
        return restaurantRepository.findAll(RestaurantSpecs.findZeroDeliveryFee());
    }
    }
