package com.mont.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mont.algafoodapi.domain.model.PaymentMethod;


public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
