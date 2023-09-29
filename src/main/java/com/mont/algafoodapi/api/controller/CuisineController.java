package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.CuisineDto;
import com.mont.algafoodapi.api.model.input.CuisineInputDto;
import com.mont.algafoodapi.api.openapi.controller.CuisineControllerOpenApi;
import com.mont.algafoodapi.core.security.CheckSecurity;
import com.mont.algafoodapi.domain.model.Cuisine;
import com.mont.algafoodapi.domain.repository.CuisineRepository;
import com.mont.algafoodapi.domain.service.CuisineService;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/cuisine", produces = MediaType.APPLICATION_JSON_VALUE)
public class CuisineController implements CuisineControllerOpenApi {
    
    @Autowired
    private CuisineService cuisineService;

    @Autowired
    private CuisineRepository cuisineRepository;


    @CheckSecurity.Cuisine.allowsQuery
    @GetMapping
    public ResponseEntity<Page<CuisineDto>> findAll(
        @PageableDefault(size = 10) @Nullable Pageable pageable
        ) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return ResponseEntity.status(HttpStatus.OK).body(cuisineService.findAll(pageable));
    }

    @CheckSecurity.Cuisine.allowsQuery
    @GetMapping("/{id}")
    public ResponseEntity<CuisineDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cuisineService.findById(id));
    }

    @CheckSecurity.Cuisine.allowsEdit
    @PostMapping
    public ResponseEntity<CuisineDto> create(@RequestBody @Valid CuisineInputDto cuisineInputDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuisineService.create(cuisineInputDto));
    }

    @CheckSecurity.Cuisine.allowsEdit
    @PutMapping("/{id}")
    public ResponseEntity<CuisineDto> update(@PathVariable Long id, @RequestBody @Valid CuisineInputDto cuisineInputDto) {
        return ResponseEntity.status(HttpStatus.OK).body(cuisineService.update(id, cuisineInputDto));
    }

    @CheckSecurity.Cuisine.allowsEdit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cuisineService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Cuisine.allowsQuery
    @GetMapping("/by-name")
    public ResponseEntity<List<Cuisine>> findByName(@RequestParam String name) {
                return ResponseEntity.ok(cuisineRepository.findByName(name));

    }
 
}
