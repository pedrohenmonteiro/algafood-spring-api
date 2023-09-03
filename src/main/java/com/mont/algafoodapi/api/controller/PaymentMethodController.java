package com.mont.algafoodapi.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.mont.algafoodapi.api.model.PaymentMethodDto;
import com.mont.algafoodapi.api.model.input.PaymentMethodInputDto;
import com.mont.algafoodapi.domain.repository.PaymentMethodRepository;
import com.mont.algafoodapi.domain.service.PaymentMethodService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentMethodController {
    
    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @GetMapping
    public ResponseEntity<List<PaymentMethodDto>> findAll(ServletWebRequest request) {

        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime lastUpdateDate = paymentMethodRepository.getLastUpdateDate();
        
        if(lastUpdateDate != null) {
            eTag = String.valueOf(lastUpdateDate.toEpochSecond());
        }

        if(request.checkNotModified(eTag)) {
            return null;
        }


        return ResponseEntity.ok()
        .cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS))
        .eTag(eTag)
        .body(paymentMethodService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDto> findById(@PathVariable Long id, ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime lastUpdateDate = paymentMethodRepository.getLastUpdateById(id);

        if(lastUpdateDate != null) {
            eTag = String.valueOf(lastUpdateDate);
        }

        if(request.checkNotModified(eTag)) {
            return null;
        }


        return ResponseEntity.ok()
        .cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePublic())
        .eTag(eTag)
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
