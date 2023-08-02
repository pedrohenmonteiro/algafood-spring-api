package com.mont.algafoodapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mont.algafoodapi.domain.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    

    @Query("from Order o join fetch o.client join fetch o.restaurant r join fetch r.cuisine")
    List<Order> findAll();
}
