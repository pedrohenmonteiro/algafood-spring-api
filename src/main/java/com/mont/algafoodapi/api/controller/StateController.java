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

import com.mont.algafoodapi.api.model.StateDto;
import com.mont.algafoodapi.api.model.input.StateInputDto;
import com.mont.algafoodapi.api.openapi.controller.StateControllerOpenApi;
import com.mont.algafoodapi.domain.service.StateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/states", produces = MediaType.APPLICATION_JSON_VALUE)
public class StateController implements StateControllerOpenApi{
    
    @Autowired
    private StateService stateService;


    @GetMapping
    public ResponseEntity<List<StateDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(stateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(stateService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StateDto> create(@RequestBody @Valid StateInputDto state) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stateService.create(state));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateDto> update(@PathVariable Long id, @RequestBody @Valid StateInputDto stateInputDto) {
        return ResponseEntity.status(HttpStatus.OK).body(stateService.update(id, stateInputDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
