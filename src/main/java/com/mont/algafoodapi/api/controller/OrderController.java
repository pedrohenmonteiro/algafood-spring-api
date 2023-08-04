package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.OrderDto;
import com.mont.algafoodapi.api.model.OrderSummaryDto;
import com.mont.algafoodapi.api.model.input.OrderInputDto;
import com.mont.algafoodapi.domain.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderSummaryDto>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{orderCode}")
    public ResponseEntity<OrderDto> findById(@PathVariable String orderCode) {
        return ResponseEntity.ok(orderService.findByCode(orderCode));
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderInputDto orderInputDto) {
        return ResponseEntity.ok(orderService.create(orderInputDto));
    }
}
