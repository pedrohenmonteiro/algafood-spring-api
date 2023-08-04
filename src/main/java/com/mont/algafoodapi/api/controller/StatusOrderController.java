package com.mont.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.domain.service.StatusOrderService;

@RestController
@RequestMapping("/orders/{orderId}")
public class StatusOrderController {
    
    @Autowired
    private StatusOrderService statusOrderService;


    @PutMapping("/confirmation")
    public ResponseEntity<Void> confirm(@PathVariable Long orderId) {
        statusOrderService.confirm(orderId);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/delivered")
    public ResponseEntity<Void> delivered(@PathVariable Long orderId) {
        statusOrderService.delivered(orderId);
       return ResponseEntity.noContent().build();
    }

     @PutMapping("/cancelation")
    public ResponseEntity<Void> cancel(@PathVariable Long orderId) {
        statusOrderService.canceled(orderId);
       return ResponseEntity.noContent().build();
    }
}
