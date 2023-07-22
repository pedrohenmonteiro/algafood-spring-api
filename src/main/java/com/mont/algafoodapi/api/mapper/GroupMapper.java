package com.mont.algafoodapi.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.GroupDto;
import com.mont.algafoodapi.api.model.input.GroupInputDto;
import com.mont.algafoodapi.domain.model.Group;

@Component
public class GroupMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public Group fromDtoToEntity(GroupInputDto groupInputDto) {
        return modelMapper.map(groupInputDto, Group.class);
    }

    public GroupDto fromEntityToDto(Group group) {
        return modelMapper.map(group, GroupDto.class);
    }

    public List<GroupDto> toCollectionDto(List<Group> groups) {
        return groups.stream().map(this::fromEntityToDto).toList();
    }

    public void copyToDomainObject(GroupInputDto groupInputDto, Group group) {
        modelMapper.map(groupInputDto, group);
    }
}
