package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.GroupDto;
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
}
