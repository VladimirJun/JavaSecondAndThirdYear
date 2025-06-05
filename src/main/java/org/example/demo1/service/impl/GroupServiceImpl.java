package org.example.demo1.service.impl;


import org.example.demo1.dto.group.CreateGroupDto;
import org.example.demo1.dto.group.GroupDto;
import org.example.demo1.dto.group.UpdateGroupDto;
import org.example.demo1.entity.GroupEntity;
import org.example.demo1.exception.NotFoundException;
import org.example.demo1.mapper.GroupMapper;
import org.example.demo1.repository.GroupRepository;
import org.example.demo1.repository.StudentRepository;
import org.example.demo1.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private static final String ENTITY = "Group";

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository,
                            StudentRepository studentRepository,
                            GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public Long addGroup(CreateGroupDto groupDto) {
        GroupEntity group = this.groupMapper.mapToEntityFromCreateDto(groupDto);
        return groupRepository.save(group).getId();
    }

    @Override
    public void editGroup(Long id, UpdateGroupDto updateGroupDto) {
        GroupEntity group = this.groupRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ENTITY, id)
        );

        this.groupMapper.updateEntityFromDto(updateGroupDto, group);
        this.groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Long id) {
        GroupEntity group = this.groupRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ENTITY, id)
        );
        this.groupRepository.delete(group);
    }

    @Override
    @Transactional(readOnly = true)
    public GroupDto getGroupById(Long id) {
        GroupEntity group = this.groupRepository.findByIdWithStudents(id).orElseThrow(
                () -> new NotFoundException(ENTITY, id)
        );
        return this.groupMapper.mapToDto(group);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupDto> getGroups() {
        List<GroupEntity> groups = this.groupRepository.findAllWithStudents();
        return this.groupMapper.mapToDtos(groups);
    }
}