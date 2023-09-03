package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.GroupMapper;
import com.mont.algafoodapi.api.model.GroupDto;
import com.mont.algafoodapi.domain.repository.UserRepository;

@Service
public class UserGroupService {
    
    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserRepository userRepository;

    public List<GroupDto> findAll(Long userId) {
        var user = userService.getUser(userId);
        return groupMapper.toCollectionDto(user.getGroups());
    }

    public void associateGroup(Long userId, Long groupId) {
        var user = userService.getUser(userId);
        var group = groupService.getGroup(groupId);

        user.addGroup(group);
        userRepository.save(user);
    }

    public void disassociateGroup(Long userId, Long groupId) {
        var user = userService.getUser(userId);
        var group = groupService.getGroup(groupId);

        user.removeGroup(group);
        userRepository.save(user);
    }
}
