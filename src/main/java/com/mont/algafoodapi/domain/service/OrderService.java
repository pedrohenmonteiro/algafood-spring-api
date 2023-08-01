package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.OrderMapper;
import com.mont.algafoodapi.api.mapper.OrderSummaryMapper;
import com.mont.algafoodapi.api.model.OrderDto;
import com.mont.algafoodapi.api.model.OrderSummaryDto;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Order;
import com.mont.algafoodapi.domain.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderSummaryMapper orderSummaryMapper;
    
    public List<OrderSummaryDto> findAll() {
        return orderSummaryMapper.toCollectionDto(orderRepository.findAll());
    }

    public OrderDto findById(Long id) {
        var order = getOrder(id);
        return orderMapper.fromEntityToDto(order);
    }

    protected Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource order id " + id + " not found"));
    }
}
