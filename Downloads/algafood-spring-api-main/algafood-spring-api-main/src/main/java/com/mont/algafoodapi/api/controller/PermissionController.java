package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.PermissionDto;
import com.mont.algafoodapi.core.security.CheckSecurity;
import com.mont.algafoodapi.domain.service.PermissionService;

@RestController
@RequestMapping(path = "/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController {
    
    @Autowired
    private PermissionService permissionService;
    
    
    @CheckSecurity.UserGroupsPermissions.allowsQuery
    @GetMapping
    public ResponseEntity<List<PermissionDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(permissionService.findAll());
    }
}
