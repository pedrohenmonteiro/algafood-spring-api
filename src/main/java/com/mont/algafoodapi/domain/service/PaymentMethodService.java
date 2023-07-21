package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.PaymentMethodMapper;
import com.mont.algafoodapi.api.model.PaymentMethodDto;
import com.mont.algafoodapi.domain.repository.PaymentMethodRepository;

@Service
public class PaymentMethodService {
    
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;


    public List<PaymentMethodDto> findAll() {
        return paymentMethodMapper.toCollectionDto(paymentMethodRepository.findAll());
    }
}
