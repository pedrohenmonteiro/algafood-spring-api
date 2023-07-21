package com.mont.algafoodapi.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.PaymentMethodDto;
import com.mont.algafoodapi.api.model.input.PaymentMethodInputDto;

@Component
public class PaymentMethod {

    @Autowired
    private ModelMapper modelMapper;
    
    public PaymentMethod fromDtoToEntity(PaymentMethodInputDto paymentMethodInputDto) {
        return modelMapper.map(paymentMethodInputDto, PaymentMethod.class);
    }
    
    public PaymentMethodDto fromEntityToDto(PaymentMethod paymentMethod) {
        return modelMapper.map(paymentMethod, PaymentMethodDto.class);
    }

    public List<PaymentMethodDto> toCollectionDto(List<PaymentMethod> paymentMethods) {
        return paymentMethods.stream().map(this::fromEntityToDto).toList();
    }

    public void copyToDomainObject(PaymentMethodInputDto paymentMethodInputDto, PaymentMethod paymentMethod) {
        modelMapper.map(paymentMethodInputDto, paymentMethod);
    }
}
