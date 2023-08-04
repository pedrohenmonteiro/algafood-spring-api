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
     setOrderStatus(orderId, OrderStatus.CONFIRMED, OrderStatus.CREATED);
    }


    @Transactional
    public void delivered(Long orderId) {
        setOrderStatus(orderId, OrderStatus.DELIVERED, OrderStatus.CONFIRMED);
    }

    @Transactional
    public void canceled(Long orderId) {
        setOrderStatus(orderId, OrderStatus.CANCELED, OrderStatus.CREATED);
    }




        private void setOrderStatus(Long orderId, OrderStatus orderStatusAfter, OrderStatus orderStatusBefore) {
            var order = orderService.getOrder(orderId);

            if (!order.getStatus().equals(orderStatusBefore)) {
            throw new BadRequestException(String.format("Order status %d can not be changed from %s to %s", 
                order.getId(), order.getStatus(), orderStatusAfter
            ));
        }
        order.setStatus(orderStatusAfter);

        setStatusDate(order, orderStatusBefore);

        }

        private void setStatusDate(Order order, OrderStatus orderStatus) {
        var statusDate = OffsetDateTime.now();

         if(orderStatus == OrderStatus.CONFIRMED) {
                order.setConfirmDate(statusDate);
            } else if (orderStatus == OrderStatus.CONFIRMED) {
                order.setDeliveredDate(statusDate);
            } else if (orderStatus == OrderStatus.CANCELED) {
                order.setCancelDate(statusDate);
            }
        }
}
