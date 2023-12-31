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

import com.mont.algafoodapi.api.model.GroupDto;
import com.mont.algafoodapi.api.model.input.GroupInputDto;
import com.mont.algafoodapi.api.openapi.controller.GroupControllerOpenApi;
import com.mont.algafoodapi.core.security.CheckSecurity;
import com.mont.algafoodapi.domain.service.GroupService;

@RestController
@RequestMapping(path = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController implements GroupControllerOpenApi{
    
    @Autowired
    private GroupService groupService;

    @CheckSecurity.UserGroupsPermissions.allowsQuery
    @GetMapping
    public ResponseEntity<List<GroupDto>> findAll() {
        return ResponseEntity.ok(groupService.findAll());
    }

    @CheckSecurity.UserGroupsPermissions.allowsQuery
    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.findById(id));
    }

    @CheckSecurity.UserGroupsPermissions.allowsEdit
    @PostMapping
    public ResponseEntity<GroupDto> create(@RequestBody GroupInputDto groupInputDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.create(groupInputDto));
    }

    @CheckSecurity.UserGroupsPermissions.allowsEdit
    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> update(@PathVariable Long id, @RequestBody GroupInputDto groupInputDto) {
        return ResponseEntity.ok(groupService.update(id, groupInputDto));
    }
    
    @CheckSecurity.UserGroupsPermissions.allowsEdit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity.noContent().build();
    }
 
}
