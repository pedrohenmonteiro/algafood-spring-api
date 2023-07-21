package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.PaymentMethodMapper;
import com.mont.algafoodapi.api.model.PaymentMethodDto;
import com.mont.algafoodapi.api.model.input.PaymentMethodInputDto;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.PaymentMethod;
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

    public PaymentMethodDto findById(Long id) {
        return paymentMethodMapper.fromEntityToDto(getPaymentMethod(id));
    }

    public PaymentMethodDto create(PaymentMethodInputDto paymentMethodInputDto) {
        var paymentMethod = paymentMethodMapper.fromDtoToEntity(paymentMethodInputDto);
        return paymentMethodMapper.fromEntityToDto(paymentMethodRepository.save(paymentMethod));
    }
    
    public PaymentMethodDto update(Long id, PaymentMethodInputDto paymentMethodInputDto) {
        var paymentMethod = getPaymentMethod(id);
        paymentMethodMapper.copyToDomainObject(paymentMethodInputDto, paymentMethod);
        return paymentMethodMapper.fromEntityToDto(paymentMethodRepository.save(paymentMethod));

    }


    private PaymentMethod getPaymentMethod(Long id) {
        return paymentMethodRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found"));
    }


}
