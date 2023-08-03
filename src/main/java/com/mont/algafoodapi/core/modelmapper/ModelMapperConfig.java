package com.mont.algafoodapi.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mont.algafoodapi.api.model.input.OrderItemInputDto;
import com.mont.algafoodapi.domain.model.OrderItem;


@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();


    // modelMapper.createTypeMap(OrderItemInputDto.class, OrderItem.class).addMappings(mapper -> mapper.skip(OrderItem::setId));
    // modelMapper.createTypeMap(OrderItemInputDto.class, OrderItem.class)
    // .addMappings(mapper -> mapper.skip(OrderItem::setId));

    return modelMapper;
    }
}
