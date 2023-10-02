package com.mont.algafoodapi.domain.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mont.algafoodapi.domain.model.PaymentMethod;


public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Query("select max(updateDate) from PaymentMethod")
    OffsetDateTime getLastUpdateDate();


    @Query("select updateDate from PaymentMethod where id = :id")
    OffsetDateTime getLastUpdateById(Long id);
}
