package com.mont.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
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
import com.mont.algafoodapi.api.openapi.controller.OrderControllerOpenApi;
import com.mont.algafoodapi.core.security.CheckSecurity;
import com.mont.algafoodapi.domain.filter.OrderFilter;
import com.mont.algafoodapi.domain.repository.OrderRepository;
import com.mont.algafoodapi.domain.service.OrderService;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController implements OrderControllerOpenApi {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @CheckSecurity.Orders.allowsSearch
    @GetMapping
    public ResponseEntity<Page<OrderSummaryDto>> findAll(@PageableDefault(size = 10) @Nullable Pageable pageable, @Nullable OrderFilter filter) {
        return ResponseEntity.ok(orderService.findAll(filter, pageable));
    }

    @CheckSecurity.Orders.allowsQuery
    @GetMapping("/{orderCode}")
    public ResponseEntity<OrderDto> findByCode(@PathVariable String orderCode) {
        return ResponseEntity.ok(orderService.findByCode(orderCode));
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody @Valid OrderInputDto orderInputDto) {
        return ResponseEntity.ok(orderService.create(orderInputDto));
    }


}
