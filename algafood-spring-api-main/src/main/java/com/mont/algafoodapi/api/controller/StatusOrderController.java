package com.mont.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.domain.service.StatusOrderService;

@RestController
@RequestMapping("/orders/{orderCode}")
public class StatusOrderController {
    
    @Autowired
    private StatusOrderService statusOrderService;


    @PutMapping("/confirmation")
    public ResponseEntity<Void> confirm(@PathVariable String orderCode) {
        statusOrderService.confirm(orderCode);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/delivered")
    public ResponseEntity<Void> delivered(@PathVariable String orderCode) {
        statusOrderService.delivered(orderCode);
       return ResponseEntity.noContent().build();
    }

     @PutMapping("/cancelation")
    public ResponseEntity<Void> cancel(@PathVariable String orderCode) {
        statusOrderService.canceled(orderCode);
       return ResponseEntity.noContent().build();
    }
}
