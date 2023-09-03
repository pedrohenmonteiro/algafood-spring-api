package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.GroupDto;
import com.mont.algafoodapi.domain.service.UserGroupService;

@RestController
@RequestMapping("/users/{userId}/groups")
public class UserGroupController {
    
    @Autowired
    private UserGroupService userGroupService;

    @GetMapping
    public ResponseEntity<List<GroupDto>> findAll(@PathVariable Long userId) {
        return ResponseEntity.ok(userGroupService.findAll(userId));
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<Void> associateGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        userGroupService.associateGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> disassociateGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        userGroupService.disassociateGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }
}
