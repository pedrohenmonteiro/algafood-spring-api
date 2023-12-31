package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.mont.algafoodapi.api.model.CityDto;
import com.mont.algafoodapi.api.model.input.CityInputDto;
import com.mont.algafoodapi.api.openapi.controller.CityControllerOpenApi;
import com.mont.algafoodapi.core.security.CheckSecurity;
import com.mont.algafoodapi.domain.service.CityService;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController implements CityControllerOpenApi {

    @Autowired
    private CityService cityService;

    @CheckSecurity.Cities.allowsQuery
    @GetMapping
    public ResponseEntity<List<CityDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(cityService.findAll());
    }

    @CheckSecurity.Cities.allowsQuery
    @GetMapping("/{id}")
    public ResponseEntity<CityDto> findById(@Parameter(description = "The id of the city to find") @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cityService.findById(id));
    }

    @CheckSecurity.Cities.allowsEdit
    @PostMapping
    public ResponseEntity<CityDto> create(@RequestBody @Valid CityInputDto cityInputDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.create(cityInputDto));
    }

    @CheckSecurity.Cities.allowsEdit
    @PutMapping("/{id}")
    public ResponseEntity<CityDto> update(@PathVariable Long id, @RequestBody @Valid CityInputDto cityInputDto) {
        return ResponseEntity.status(HttpStatus.OK).body(cityService.update(id, cityInputDto));
    }

    @CheckSecurity.Cities.allowsEdit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
