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

import com.mont.algafoodapi.api.model.PermissionDto;
import com.mont.algafoodapi.api.model.input.PermissionInputDto;
import com.mont.algafoodapi.domain.service.PermissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController {
    
    @Autowired
    private PermissionService permissionService;
    
    
    @GetMapping
    public ResponseEntity<List<PermissionDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(permissionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(permissionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PermissionDto> create(@RequestBody @Valid PermissionInputDto permission) {
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.create(permission));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionDto> update(@PathVariable Long id, @RequestBody @Valid PermissionInputDto permissionInputDto) {
        return ResponseEntity.status(HttpStatus.OK).body(permissionService.update(id, permissionInputDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
