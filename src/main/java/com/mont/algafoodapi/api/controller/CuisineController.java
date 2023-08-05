package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.mont.algafoodapi.domain.model.Cuisine;
import com.mont.algafoodapi.domain.repository.CuisineRepository;
import com.mont.algafoodapi.domain.service.CuisineService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cuisine")
public class CuisineController {
    


    @Autowired
    private CuisineService cuisineService;

    @Autowired
    private CuisineRepository cuisineRepository;


    @GetMapping
    public ResponseEntity<Page<CuisineDto>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(cuisineService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuisineDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cuisineService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CuisineDto> create(@RequestBody @Valid CuisineInputDto cuisineInputDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuisineService.create(cuisineInputDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuisineDto> update(@PathVariable Long id, @RequestBody @Valid CuisineInputDto cuisineInputDto) {
        return ResponseEntity.status(HttpStatus.OK).body(cuisineService.update(id, cuisineInputDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cuisineService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<Cuisine>> findByName(@RequestParam String name) {
                return ResponseEntity.ok(cuisineRepository.findByName(name));

    }

 
}
