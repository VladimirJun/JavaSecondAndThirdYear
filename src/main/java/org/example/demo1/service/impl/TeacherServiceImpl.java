package org.example.demo1.service.impl;


import org.example.demo1.dto.TeacherDto;
import org.example.demo1.entity.TeacherEntity;
import org.example.demo1.exception.NotFoundException;
import org.example.demo1.exception.ServiceException;
import org.example.demo1.mapper.TeacherMapper;
import org.example.demo1.repository.TeacherRepository;
import org.example.demo1.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private static final String ENTITY = "Teacher";

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    @Transactional
    public Long addTeacher(TeacherDto teacherRequest) {
        try {

            TeacherEntity teacher = this.teacherMapper.mapToEntity(teacherRequest);
            return this.teacherRepository.save(teacher).getId();
        } catch (Exception e) {

            throw new ServiceException("Service error on add teacher.", e);
        }
    }

    @Override
    @Transactional
    public void editTeacher(TeacherDto teacherRequest) {
        try {

            if (!this.teacherRepository.existsById(teacherRequest.id())) {
                throw new NotFoundException(ENTITY, teacherRequest.id());
            }

            TeacherEntity updatedTeacher = this.teacherMapper.mapToEntity(teacherRequest);
            this.teacherRepository.save(updatedTeacher);
        } catch (Exception e) {

            throw new ServiceException("Service error on edit teacher.", e);
        }
    }

    @Override
    @Transactional
    public void deleteTeacher(Long id) {
        try {

            if (!this.teacherRepository.existsById(id)) {

                throw new NotFoundException(ENTITY, id);
            }
            this.teacherRepository.deleteById(id);
        } catch (Exception e) {

            throw new ServiceException("Service error on delete teacher.", e);
        }
    }

    @Override
    public TeacherDto getTeacherById(Long id) {
        try {

            TeacherEntity teacher = this.teacherRepository.findById(id).orElseThrow(
                    () -> new NotFoundException(ENTITY, id)
            );
            return this.teacherMapper.mapToDto(teacher);
        } catch (Exception e) {

            throw new ServiceException("Service error on get teacher by id.", e);
        }
    }

    @Override
    public List<TeacherDto> getTeachers() {
        try {

            List<TeacherEntity> teachers = (List<TeacherEntity>) this.teacherRepository.findAll();
            return this.teacherMapper.mapToDtos(teachers);
        } catch (Exception e) {

            throw new ServiceException("Service error on get teachers.", e);
        }
    }
}