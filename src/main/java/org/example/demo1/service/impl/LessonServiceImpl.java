package org.example.demo1.service.impl;


import org.example.demo1.dto.LessonDto;
import org.example.demo1.entity.LessonEntity;
import org.example.demo1.exception.NotFoundException;
import org.example.demo1.mapper.LessonMapper;
import org.example.demo1.repository.GroupRepository;
import org.example.demo1.repository.LessonRepository;
import org.example.demo1.repository.StudentRepository;
import org.example.demo1.repository.TeacherRepository;
import org.example.demo1.service.LessonService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private static final String ENTITY = "Lesson";

    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final LessonMapper lessonMapper;

    public LessonServiceImpl(LessonRepository lessonRepository,
                             TeacherRepository teacherRepository,
                             GroupRepository groupRepository,
                             StudentRepository studentRepository,
                             LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.lessonMapper = lessonMapper;
    }

    @Override
    @Transactional
    public Long addLesson(LessonDto lessonRequest) {
        try {

            this.checkLessonDto(lessonRequest);

            LessonEntity lesson = this.lessonMapper.mapToEntity(lessonRequest);
            return this.lessonRepository.save(lesson).getId();
        } catch (Exception e) {

            throw new ServiceException("Service error on add lesson.", e);
        }
    }

    @Override
    @Transactional
    public void editLesson(LessonDto lessonRequest) {
        try {

            if (!this.lessonRepository.existsById(lessonRequest.id())) {
                throw new NotFoundException(ENTITY, lessonRequest.id());
            }

            this.checkLessonDto(lessonRequest);

            LessonEntity lesson = this.lessonMapper.mapToEntity(lessonRequest);
            this.lessonRepository.save(lesson);
        } catch (Exception e) {

            throw new ServiceException("Service error on edit lesson.", e);
        }
    }

    private void checkLessonDto(LessonDto lessonRequest) {

        if (lessonRequest.attendance() != null) {

            lessonRequest.attendance()
                    .keySet()
                    .forEach(id -> {
                        if (!studentRepository.existsById(id)) {
                            throw new NotFoundException("Student", id);
                        }
                    });
        }

        if (!this.groupRepository.existsById(lessonRequest.groupId())) {
            throw new NotFoundException("Group", lessonRequest.groupId());
        }
    }

    @Override
    @Transactional
    public void deleteLessonByTeacherId(Long teacherId) {
        try {

            if (!this.teacherRepository.existsById(teacherId)) {
                throw new NotFoundException("Teacher", teacherId);
            }

            LessonEntity lesson = this.lessonRepository.getLessonByTeacherId(teacherId).orElseThrow(
                    () -> new NotFoundException(ENTITY + "by teacherId", teacherId)
            );

            this.lessonRepository.delete(lesson);
        } catch (Exception e) {

            throw new ServiceException("Service error on delete lesson by teacher Id.", e);
        }
    }

    @Override
    @Transactional
    public void deleteLessonByGroupId(Long groupId) {
        try {

            if (!this.groupRepository.existsById(groupId)) {
                throw new NotFoundException("Group", groupId);
            }

            LessonEntity lesson = this.lessonRepository.getLessonByGroupId(groupId).orElseThrow(
                    () -> new NotFoundException(ENTITY + "by groupId", groupId)
            );

            this.lessonRepository.delete(lesson);
        } catch (Exception e) {

            throw new ServiceException("Service error on delete lesson by group Id.", e);
        }
    }

    @Override
    public LessonDto getLessonById(Long id) {
        try {

            LessonEntity lesson = this.lessonRepository.findById(id).orElseThrow(
                    () -> new NotFoundException(ENTITY, id)
            );
            return this.lessonMapper.mapToDto(lesson);
        } catch (Exception e) {

            throw new ServiceException("Service error on get lesson by id", e);
        }
    }

    @Override
    public List<LessonDto> getLessonByTeacherForPeriod(Long teacherId, LocalDate dateStart, LocalDate dateEnd) {
        try {

            if (!this.teacherRepository.existsById(teacherId)) {
                throw new NotFoundException("Teacher", teacherId);
            }

            List<LessonEntity> lessons =
                    this.lessonRepository.getLessonsByTeacherForPeriod(teacherId, dateStart, dateEnd);

            return this.lessonMapper.mapToDtos(lessons);
        } catch (Exception e) {

            throw new ServiceException("Service error on get lesson by teacher for period.", e);
        }
    }

    @Override
    public List<LessonDto> getLessonByGroupForPeriod(Long groupId, LocalDate dateStart, LocalDate dateEnd) {
        try {

            if (!this.groupRepository.existsById(groupId)) {
                throw new NotFoundException("Group", groupId);
            }

            List<LessonEntity> lessons =
                    this.lessonRepository.getLessonsByGroupForPeriod(groupId, dateStart, dateEnd);

            return this.lessonMapper.mapToDtos(lessons);
        } catch (Exception e) {

            throw new ServiceException("Service error on get lesson by group for period.", e);
        }
    }
}