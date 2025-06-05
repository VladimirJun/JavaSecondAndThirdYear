package org.example.demo1.service.impl;


import org.example.demo1.dto.lesson.LessonDto;
import org.example.demo1.dto.lesson.UpdateLessonDto;
import org.example.demo1.entity.GroupEntity;
import org.example.demo1.entity.LessonEntity;
import org.example.demo1.entity.StudentEntity;
import org.example.demo1.entity.TeacherEntity;
import org.example.demo1.exception.NotFoundException;
import org.example.demo1.mapper.LessonMapper;
import org.example.demo1.repository.GroupRepository;
import org.example.demo1.repository.LessonRepository;
import org.example.demo1.repository.StudentRepository;
import org.example.demo1.repository.TeacherRepository;
import org.example.demo1.service.LessonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Service
@Transactional
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
    public Long addLesson(LessonDto lessonDto) {
        this.checkLessonDto(lessonDto.teacherId(), lessonDto.groupId(), lessonDto.attendance());

        LessonEntity lesson = this.lessonMapper.mapToEntity(lessonDto);
        return this.lessonRepository.save(lesson).getId();
    }

    @Override
    public void editLesson(Long id, UpdateLessonDto updateLessonDto) {
        LessonEntity lesson = this.lessonRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ENTITY, id)
        );
        this.checkLessonDto(updateLessonDto.teacherId(), updateLessonDto.groupId(), updateLessonDto.attendance());

        this.updateEntityFromDto(updateLessonDto, lesson);
        this.lessonRepository.save(lesson);
    }

    private void checkLessonDto(Long teacherId, Long groupId, Map<Long, Boolean> attendance) {
        this.validateExistTeacher(teacherId);
        this.validateExistGroup(groupId);

        if (attendance != null) {
            attendance.keySet().forEach(id -> {
                StudentEntity student = this.studentRepository.findById(id).orElseThrow(
                        () -> new NotFoundException("Student", id)
                );
                if (!student.getGroup().getId().equals(groupId)) {
                    throw new NotFoundException("Student", id);
                }
            });
        }
    }

    private void updateEntityFromDto(UpdateLessonDto updateLessonDto, LessonEntity lesson) {
        TeacherEntity teacher = this.teacherRepository.getReferenceById(updateLessonDto.teacherId());
        GroupEntity group = this.groupRepository.getReferenceById(updateLessonDto.groupId());

        lesson.setTeacher(teacher);
        lesson.setGroup(group);
        lesson.setDate(updateLessonDto.date());
        lesson.setNumberOfLesson(updateLessonDto.numberOfLesson());

        if (updateLessonDto.attendance() != null) {
            var attendance = updateLessonDto.attendance().entrySet()
                    .stream()
                    .collect(toMap(
                            entry -> this.studentRepository.getReferenceById(entry.getKey()),
                            Map.Entry::getValue
                    ));
            lesson.setStudentAttendance(attendance);
        }
    }

    @Override
    public void deleteLessonsByTeacherId(Long teacherId) {
        this.validateExistTeacher(teacherId);

        List<LessonEntity> lessons = this.lessonRepository.getLessonsByTeacherId(teacherId);
        this.lessonRepository.deleteAll(lessons);
    }

    @Override
    public void deleteLessonsByGroupId(Long groupId) {
        this.validateExistGroup(groupId);

        List<LessonEntity> lessons = this.lessonRepository.getLessonsByGroupId(groupId);
        this.lessonRepository.deleteAll(lessons);
    }

    @Override
    @Transactional(readOnly = true)
    public LessonDto getLessonById(Long id) {
        LessonEntity lesson = this.lessonRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ENTITY, id)
        );
        return this.lessonMapper.mapToDto(lesson);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonDto> getLessonsByTeacherForPeriod(Long teacherId, LocalDate dateStart, LocalDate dateEnd) {
        this.validateExistTeacher(teacherId);

        List<LessonEntity> lessons =
                this.lessonRepository.getLessonsByTeacherForPeriod(teacherId, dateStart, dateEnd);
        return this.lessonMapper.mapToDtos(lessons);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonDto> getLessonsByGroupForPeriod(Long groupId, LocalDate dateStart, LocalDate dateEnd) {
        this.validateExistGroup(groupId);

        List<LessonEntity> lessons =
                this.lessonRepository.getLessonsByGroupForPeriod(groupId, dateStart, dateEnd);
        return this.lessonMapper.mapToDtos(lessons);
    }

    private void validateExistTeacher(Long teacherId) {
        if (!this.teacherRepository.existsById(teacherId)) {
            throw new NotFoundException("Teacher", teacherId);
        }
    }

    private void validateExistGroup(Long groupId) {
        if (!this.groupRepository.existsById(groupId)) {
            throw new NotFoundException("Group", groupId);
        }
    }
}