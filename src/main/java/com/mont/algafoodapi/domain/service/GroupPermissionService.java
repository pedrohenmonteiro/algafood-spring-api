package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.PermissionMapper;
import com.mont.algafoodapi.api.model.PermissionDto;
import com.mont.algafoodapi.domain.repository.GroupRepository;

@Service
public class GroupPermissionService {
    
    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired PermissionMapper permissionMapper;

    public List<PermissionDto> findAll(Long groupId) {
        var group = groupService.getGroup(groupId);
        return permissionMapper.toCollectionDto(group.getPermissions());
    }

    public void associatePermission(Long groupId, Long permissionId) {
        var group = groupService.getGroup(groupId);
        var permission = permissionService.getPermission(permissionId);
        group.addPermission(permission);
        groupRepository.save(group);
    }

    public void disassociatePermission(Long groupId, Long permissionId) {
        var group = groupService.getGroup(groupId);
        var permission = permissionService.getPermission(permissionId);
        group.removePermission(permission);
        groupRepository.save(group);
    }

    }

