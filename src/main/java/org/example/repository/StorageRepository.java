package org.example.repository;


import org.example.entity.GroupEntity;
import org.example.entity.StudentEntity;
import org.example.entity.SubjectEntity;
import org.example.entity.TeacherEntity;

public interface StorageRepository {
    EntityRepository<GroupEntity> getGroupRepository();
    EntityRepository<StudentEntity> getStudentRepository();
    EntityRepository<TeacherEntity> getTeacherRepository();
    EntityRepository<SubjectEntity> getSubjectRepository();
    LessonRepository getLessonRepository();
    AttendanceRepository getAttendanceRepository();
}