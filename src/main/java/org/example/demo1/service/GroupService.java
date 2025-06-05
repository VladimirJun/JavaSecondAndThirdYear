package org.example.demo1.service;


import org.example.demo1.dto.group.CreateGroupDto;
import org.example.demo1.dto.group.GroupDto;
import org.example.demo1.dto.group.UpdateGroupDto;

import java.util.List;

public interface GroupService {
    Long addGroup(CreateGroupDto groupDto);

    void editGroup(Long id, UpdateGroupDto updateGroupDto);

    void deleteGroup(Long id);

    GroupDto getGroupById(Long id);

    List<GroupDto> getGroups();
}