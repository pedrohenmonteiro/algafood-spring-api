package com.mont.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mont.algafoodapi.domain.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
