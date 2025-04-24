package org.example.demo1.service.impl;


import org.example.demo1.dto.GroupDto;
import org.example.demo1.entity.GroupEntity;
import org.example.demo1.exception.NotFoundException;
import org.example.demo1.mapper.GroupMapper;
import org.example.demo1.repository.GroupRepository;
import org.example.demo1.repository.StudentRepository;
import org.example.demo1.service.GroupService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private static final String ENTITY = "Group";

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final GroupMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository,
                            StudentRepository studentRepository,
                            GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    @Transactional
    public Long addGroup(GroupDto groupRequest) {
        try {

            if (groupRequest.studentsIds() != null) {

                groupRequest.studentsIds().forEach(id -> {
                    if (!studentRepository.existsById(id)) {
                        throw new NotFoundException("Student", id);
                    }
                });
            }

            GroupEntity group = this.groupMapper.mapToEntity(groupRequest);
            return groupRepository.save(group).getId();
        } catch (Exception e) {

            throw new ServiceException("Service error on add group.", e);
        }
    }

    @Override
    @Transactional
    public void editGroup(GroupDto groupRequest) {
        try {

            if (!this.groupRepository.existsById(groupRequest.id())) {
                throw new NotFoundException(ENTITY, groupRequest.id());
            }

            if (groupRequest.studentsIds() != null) {

                groupRequest.studentsIds().forEach(id -> {
                    if (!studentRepository.existsById(id)) {
                        throw new NotFoundException("Student", id);
                    }
                });
            }

            GroupEntity group = this.groupMapper.mapToEntity(groupRequest);
            this.groupRepository.save(group);
        } catch (Exception e) {

            throw new ServiceException("Service error on edit group.", e);
        }
    }

    @Override
    @Transactional
    public void deleteGroup(Long id) {
        try {

            if (!this.groupRepository.existsById(id)) {
                throw new NotFoundException(ENTITY, id);
            }
            this.groupRepository.deleteById(id);
        } catch (Exception e) {

            throw new ServiceException("Service error on delete group.", e);
        }
    }

    @Override
    public GroupDto getGroupById(Long id) {
        try {

            GroupEntity group = this.groupRepository.findById(id).orElseThrow(
                    () -> new NotFoundException(ENTITY, id)
            );
            return this.groupMapper.mapToDto(group);
        } catch (Exception e) {

            throw new ServiceException("Service error on get group by id.", e);
        }
    }

    @Override
    public List<GroupDto> getGroups() {
        try {

            List<GroupEntity> groups = (List<GroupEntity>) this.groupRepository.findAll();
            return this.groupMapper.mapToDtos(groups);
        } catch (Exception e) {

            throw new ServiceException("Service error on getGroups.", e);
        }
    }
}