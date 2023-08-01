package com.mont.algafoodapi.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.OrderDto;
import com.mont.algafoodapi.domain.model.Order;

@Component
public class OrderMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public OrderDto fromEntityToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    public List<OrderDto> toCollectionDto(List<Order> orders) {
        return orders.stream().map(this::fromEntityToDto).toList();
    }
    
}
