package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.GroupDto;
import com.mont.algafoodapi.api.model.input.GroupInputDto;
import com.mont.algafoodapi.domain.service.GroupService;

@RestController
@RequestMapping("/groups")
public class GroupController {
    
    @Autowired
    private GroupService groupService;

    @GetMapping
    private ResponseEntity<List<GroupDto>> findAll() {
        return ResponseEntity.ok(groupService.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<GroupDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.findById(id));
    }

    @PostMapping
    private ResponseEntity<GroupDto> create(@RequestBody GroupInputDto groupInputDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.create(groupInputDto));
    }

    @PutMapping("/{id}")
    private ResponseEntity<GroupDto> update(@PathVariable Long id, @RequestBody GroupInputDto groupInputDto) {
        return ResponseEntity.ok(groupService.update(id, groupInputDto));
    }

}
