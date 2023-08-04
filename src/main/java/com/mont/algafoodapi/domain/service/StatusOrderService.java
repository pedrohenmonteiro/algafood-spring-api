package com.mont.algafoodapi.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.exception.BadRequestException;
import com.mont.algafoodapi.domain.model.Order;
import com.mont.algafoodapi.domain.model.OrderStatus;

import jakarta.transaction.Transactional;

@Service
public class StatusOrderService {
    
    @Autowired
    private OrderService orderService;


    @Transactional
    public void confirm(Long orderId) {
        var order = orderService.getOrder(orderId);
        order.confirm();
    }

    @Transactional
    public void delivered(Long orderId) {
        var order = orderService.getOrder(orderId);
        order.deliver();
    }

    @Transactional
    public void canceled(Long orderId) {
        var order = orderService.getOrder(orderId); 
        order.cancel();       
    }

}
