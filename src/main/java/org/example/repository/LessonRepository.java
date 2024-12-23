package org.example.repository;


import org.example.entity.LessonEntity;

import java.time.LocalDate;
import java.util.Collection;

public interface LessonRepository {
    Long create(LessonEntity lesson);
    LessonEntity get(Long id);
    void update(LessonEntity lesson);
    void delete(Long id);
    Collection<LessonEntity> getLessonsByTutorId(Long id, LocalDate start, LocalDate end);
    Collection<LessonEntity> getLessonsByGroupId(Long id, LocalDate start, LocalDate end);
    void deleteLessonsByTutorId(Long id);
    void deleteLessonsByGroupId(Long id);
}