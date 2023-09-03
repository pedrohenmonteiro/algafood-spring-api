package com.mont.algafoodapi.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.OrderSummaryDto;
import com.mont.algafoodapi.domain.model.Order;

@Component
public class OrderSummaryMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public OrderSummaryDto fromEntityToDto(Order order) {
        return modelMapper.map(order, OrderSummaryDto.class);
    }

    public List<OrderSummaryDto> toCollectionDto(List<Order> orders) {
        return orders.stream().map(this::fromEntityToDto).toList();
    }
    
}
