package org.example.repository;


import org.example.entity.GroupEntity;
import org.example.entity.StudentEntity;
import org.example.entity.SubjectEntity;
import org.example.entity.TeacherEntity;
import org.example.repository.impl.*;

import java.util.HashMap;

public class HashMapStorage implements StorageRepository {

    private final EntityRepository<GroupEntity> groupRepository =
            new HashMapGroupRepository(new HashMap<>());

    private final EntityRepository<StudentEntity> studentRepository =
            new HashMapStudentRepository(new HashMap<>());

    private final EntityRepository<TeacherEntity> teacherRepository =
            new HashMapTeacherRepository(new HashMap<>());

    private final EntityRepository<SubjectEntity> subjectRepository =
            new HashMapSubjectRepository(new HashMap<>());

    private final LessonRepository lessonRepository =
            new HashMapLessonRepository(new HashMap<>());

    private final AttendanceRepository attendanceRepository =
            new HashMapAttendanceRepository(new HashMap<>());

    @Override
    public EntityRepository<GroupEntity> getGroupRepository() {
        return groupRepository;
    }

    @Override
    public EntityRepository<StudentEntity> getStudentRepository() {
        return studentRepository;
    }

    @Override
    public EntityRepository<TeacherEntity> getTeacherRepository() {
        return teacherRepository;
    }

    @Override
    public EntityRepository<SubjectEntity> getSubjectRepository() {
        return subjectRepository;
    }

    @Override
    public LessonRepository getLessonRepository() {
        return lessonRepository;
    }

    @Override
    public AttendanceRepository getAttendanceRepository() {
        return attendanceRepository;
    }
}