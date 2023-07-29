package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.GroupMapper;
import com.mont.algafoodapi.api.model.GroupDto;

@Service
public class UserGroupService {
    
    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserService userService;

    public List<GroupDto> findAll(Long userId) {
        var user = userService.getUser(userId);
        return groupMapper.toCollectionDto(user.getGroups());
    }
}
