package com.mont.algafoodapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.mont.algafoodapi.domain.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order>{
    
    
    Optional<Order> findByCode(String code);

    @Query("from Order o join fetch o.client join fetch o.restaurant r join fetch r.cuisine")
    List<Order> findAll();

    boolean isOrderManagedBy(String orderCode, Long userId);
}
