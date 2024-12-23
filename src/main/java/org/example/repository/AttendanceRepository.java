package org.example.repository;


import org.example.entity.AttendanceEntity;

public interface AttendanceRepository {
    Long create(AttendanceEntity entity);
    AttendanceEntity get(Long lessonId);
    void update(AttendanceEntity entity);
    void delete(Long lessonId);
}