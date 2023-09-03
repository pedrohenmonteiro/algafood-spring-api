package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.PermissionDto;
import com.mont.algafoodapi.domain.service.GroupPermissionService;

@RestController
@RequestMapping( path = "/groups/{groupId}/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupPermissionController {
    
    @Autowired
    private GroupPermissionService groupPermissionService;

    @GetMapping
    public ResponseEntity<List<PermissionDto>> findAll(@PathVariable Long groupId) {
        return ResponseEntity.status(HttpStatus.OK).body(groupPermissionService.findAll(groupId));
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<Void> associate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupPermissionService.associatePermission(groupId, permissionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<Void> disassociate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupPermissionService.disassociatePermission(groupId, permissionId);
        return ResponseEntity.noContent().build();
    }

}
