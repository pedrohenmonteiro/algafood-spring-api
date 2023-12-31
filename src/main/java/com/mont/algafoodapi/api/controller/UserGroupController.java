package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.GroupDto;
import com.mont.algafoodapi.core.security.CheckSecurity;
import com.mont.algafoodapi.domain.service.UserGroupService;

@RestController
@RequestMapping(path = "/users/{userId}/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserGroupController {
    
    @Autowired
    private UserGroupService userGroupService;

    @CheckSecurity.UserGroupsPermissions.allowsQuery
    @GetMapping
    public ResponseEntity<List<GroupDto>> findAll(@PathVariable Long userId) {
        return ResponseEntity.ok(userGroupService.findAll(userId));
    }

    @CheckSecurity.UserGroupsPermissions.allowsEdit
    @PutMapping("/{groupId}")
    public ResponseEntity<Void> associateGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        userGroupService.associateGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.UserGroupsPermissions.allowsEdit
    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> disassociateGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        userGroupService.disassociateGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }
}
