package com.mont.algafoodapi.api.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.webresources.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.PaymentMethodDto;
import com.mont.algafoodapi.api.model.input.PaymentMethodInputDto;
import com.mont.algafoodapi.domain.service.PaymentMethodService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {
    
    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<List<PaymentMethodDto>> findAll() {
        return ResponseEntity.ok()
        .cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS))
        .body(paymentMethodService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
        .cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePublic())
        .body(paymentMethodService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PaymentMethodDto> create(@RequestBody @Valid PaymentMethodInputDto paymentMethodInputDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentMethodService.create(paymentMethodInputDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodDto> update(@PathVariable Long id, @RequestBody @Valid PaymentMethodInputDto paymentMethodInputDto) {
        return ResponseEntity.ok(paymentMethodService.update(id, paymentMethodInputDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentMethodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
