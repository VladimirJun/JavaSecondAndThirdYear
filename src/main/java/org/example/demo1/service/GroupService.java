package org.example.demo1.service;


import org.example.demo1.dto.GroupDto;

import java.util.List;

public interface GroupService {
    Long addGroup(GroupDto groupRequest);

    void editGroup(GroupDto groupRequest);

    void deleteGroup(Long id);

    GroupDto getGroupById(Long id);

    List<GroupDto> getGroups();
}