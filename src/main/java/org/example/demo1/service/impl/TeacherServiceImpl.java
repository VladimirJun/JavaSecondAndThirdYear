package org.example.demo1.service.impl;


import org.example.demo1.dto.teacher.CreateTeacherDto;
import org.example.demo1.dto.teacher.TeacherDto;
import org.example.demo1.dto.teacher.UpdateTeacherDto;
import org.example.demo1.entity.TeacherEntity;
import org.example.demo1.entity.user.Role;
import org.example.demo1.exception.NotFoundException;
import org.example.demo1.mapper.TeacherMapper;
import org.example.demo1.repository.TeacherRepository;
import org.example.demo1.service.TeacherService;
import org.example.demo1.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private static final String ENTITY = "Teacher";

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final UserService userService;

    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              TeacherMapper teacherMapper,
                              UserService userService) {
        this.teacherRepository = teacherRepository;
        this.userService = userService;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public Long addTeacher(CreateTeacherDto teacherRequest) {

        TeacherEntity teacher = this.createAndGetTeacher(teacherRequest);
        this.userService.createUser(teacherRequest.email(), teacherRequest.password(), Role.ROLE_TEACHER, teacher);
        return teacher.getId();
    }

    private TeacherEntity createAndGetTeacher(CreateTeacherDto teacherRequest) {
        TeacherEntity teacher = this.teacherMapper.mapToEntity(teacherRequest);
        this.teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    public void editTeacher(Long id, UpdateTeacherDto updateTeacherDto) {
        TeacherEntity teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ENTITY, id));

        this.teacherMapper.updateEntityFromDto(updateTeacherDto, teacher);

        this.teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        TeacherEntity teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ENTITY, id));

        this.teacherRepository.delete(teacher);
        this.userService.deleteUserByProfile(Role.ROLE_TEACHER, teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherDto getTeacherById(Long id) {
        TeacherEntity teacher = this.teacherRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ENTITY, id)
        );
        return this.teacherMapper.mapToDto(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherDto> getTeachers() {
        List<TeacherEntity> teachers = this.teacherRepository.findAll();
        return this.teacherMapper.mapToDtos(teachers);
    }
}