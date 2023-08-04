package com.mont.algafoodapi.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.exception.BadRequestException;
import com.mont.algafoodapi.domain.model.OrderStatus;

import jakarta.transaction.Transactional;

@Service
public class StatusOrderService {
    
    @Autowired
    private OrderService orderService;

    @Transactional
    public void confirm(Long orderId) {
        var order = orderService.getOrder(orderId);

        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new BadRequestException(String.format("Order status %d can not be changed from %s to %s", 
                order.getId(), order.getStatus(), OrderStatus.CONFIRMED
            ));
        }

        order.setStatus(OrderStatus.CONFIRMED);
        order.setConfirmDate(OffsetDateTime.now());
    }
}
