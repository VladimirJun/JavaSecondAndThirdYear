package org.example.service;

import org.example.entity.*;
import org.example.exception.ServiceException;
import org.example.model.*;
import org.example.repository.AttendanceRepository;
import org.example.repository.EntityRepository;
import org.example.repository.LessonRepository;
import org.example.request.*;
import org.example.utilities.DateConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class LessonService {

    public final LessonRepository lessonRepository;
    private final EntityRepository<StudentEntity> studentRepository;
    private final EntityRepository<GroupEntity> groupRepository;
    private final EntityRepository<TeacherEntity> teacherRepository;
    private final EntityRepository<SubjectEntity> subjectRepository;
    private final AttendanceRepository attendanceRepository;

    public LessonService(
            LessonRepository lessonRepository,
            EntityRepository<StudentEntity> studentRepository,
            EntityRepository<GroupEntity> groupRepository,
            EntityRepository<TeacherEntity> teacherRepository,
            EntityRepository<SubjectEntity> subjectRepository,
            AttendanceRepository attendanceRepository
    ) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.attendanceRepository = attendanceRepository;
    }

    // GET
    public Collection<LessonModel> getLessonsByGroup(GetLessonsByGroupRequest request) {
        return lessonRepository.getLessonsByGroupId(
                request.groupId(),
                DateConverter.parseDate(request.beginDate()),
                DateConverter.parseDate(request.endDate())
        ).stream().map(this::constructModel).toList();
    }

    // GET
    public Collection<LessonModel> getLessonsByTeacher(GetLessonsByTeacherRequest request) {
        return lessonRepository.getLessonsByGroupId(
                request.teacherId(),
                DateConverter.parseDate(request.beginDate()),
                DateConverter.parseDate(request.endDate())
        ).stream().map(this::constructModel).toList();
    }

    // GET
    public LessonModel getLessonById(IdRequest request) {
        final LessonEntity entity = lessonRepository.get(request.id());
        return entity == null ? null : constructModel(entity);
    }

    // POST
    public long addLesson(AddLessonRequest request) {
        final LessonEntity lessonEntity = new LessonEntity(
                null,
                request.subjectId(),
                DateConverter.parseDate(request.date()),
                request.number(),
                request.teacherId(),
                request.groupId()
        );

        final Long lessonId = lessonRepository.create(lessonEntity);
        final AttendanceEntity reportEntity = new AttendanceEntity(
                null,
                lessonId,
                request.studentsId()
        );
        attendanceRepository.create(reportEntity);

        return lessonId;
    }

    // PATCH
    public void editLesson(EditLessonRequest request) {
        final LessonEntity lesson = Optional.ofNullable(lessonRepository.get(request.id()))
                .orElseThrow(() -> new ServiceException("No lesson entity with id " + request.id() + " found"));

        final LessonEntity lessonEntity = new LessonEntity(
                lesson.id(),
                request.subjectId() == null ? lesson.subjectId() : request.subjectId(),
                request.date() == null ? lesson.date() : DateConverter.parseDate(request.date()),
                request.number() == null ? lesson.number() : request.number(),
                request.teacherId() == null ? lesson.teacherId() : request.teacherId(),
                request.groupId() == null ? lesson.groupId() : request.groupId()
        );

        lessonRepository.update(lessonEntity);

        final AttendanceEntity report = Optional.ofNullable(attendanceRepository.get(lessonEntity.id()))
                .orElseThrow(() -> new ServiceException("No lesson report entity for lesson id " + lessonEntity.id() + " found"));

        final AttendanceEntity reportEntity = new AttendanceEntity(
                report.id(),
                lessonEntity.id(),
                request.studentsId()
        );

        attendanceRepository.update(reportEntity);
    }

    // DELETE
    public void deleteLesson(IdRequest request) {
        lessonRepository.delete(request.id());
        attendanceRepository.delete(request.id());
    }

    private LessonModel constructModel(LessonEntity lessonEntity) {
        final SubjectModel subject = Optional.ofNullable(subjectRepository.get(lessonEntity.subjectId()))
                .map(e -> new SubjectModel(e.id(), e.name()))
                .orElseThrow(() -> new ServiceException("No subject entity with id " + lessonEntity.teacherId() + " found"));

        final GroupModel group = Optional.ofNullable(groupRepository.get(lessonEntity.groupId()))
                .map(e -> new GroupModel(e.id(), e.name()))
                .orElseThrow(() -> new ServiceException("No group entity with id " + lessonEntity.groupId() + " found"));

        final TeacherModel tutor = Optional.ofNullable(teacherRepository.get(lessonEntity.teacherId()))
                .map(e -> new TeacherModel(e.id(), e.firstName(), e.patronymic(), e.lastName()))
                .orElseThrow(() -> new ServiceException("No teacher entity with id " + lessonEntity.teacherId() + " found"));

        final long lessonId = lessonEntity.id();
        final AttendanceEntity reportEntity = Optional.ofNullable(attendanceRepository.get(lessonId))
                .orElseThrow(() -> new ServiceException("No lesson report for lesson id " + lessonId + " found"));

        final List<StudentModel> students = new ArrayList<>();
        reportEntity.studentsId().forEach(id -> {
            final StudentModel student = Optional.ofNullable(studentRepository.get(id))
                    .map(e -> new StudentModel(e.id(), e.firstName(), e.lastName(), e.patronymic(), e.status(), group))
                    .orElseThrow(() -> new ServiceException("No student entity with id " + id + " found"));
            students.add(student);
        });

        final AttendanceModel report = new AttendanceModel(reportEntity.id(), reportEntity.lessonId(), students);

        return new LessonModel(
                lessonId,
                subject,
                lessonEntity.date(),
                lessonEntity.number(),
                tutor,
                group,
                report
        );
    }
}