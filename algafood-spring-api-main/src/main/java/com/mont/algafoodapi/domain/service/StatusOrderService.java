package com.mont.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StatusOrderService {
    
    @Autowired
    private OrderService orderService;


    @Transactional
    public void confirm(String orderCode) {
        var order = orderService.getOrder(orderCode);
        order.confirm();
    }

    @Transactional
    public void delivered(String orderCode) {
        var order = orderService.getOrder(orderCode);
        order.deliver();
    }

    @Transactional
    public void canceled(String orderCode) {
        var order = orderService.getOrder(orderCode); 
        order.cancel();       
    }

}
