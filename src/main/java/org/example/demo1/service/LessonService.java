package org.example.demo1.service;



import org.example.demo1.dto.LessonDto;

import java.time.LocalDate;
import java.util.List;

public interface LessonService {
    Long addLesson(LessonDto lessonRequest);

    void editLesson(LessonDto lessonRequest);

    void deleteLessonByTeacherId(Long teacherId);

    void deleteLessonByGroupId(Long groupId);

    LessonDto getLessonById(Long lessonId);

    List<LessonDto> getLessonByTeacherForPeriod(Long teacherId, LocalDate dateStart, LocalDate dateEnd);

    List<LessonDto> getLessonByGroupForPeriod(Long groupId, LocalDate dateStart, LocalDate dateEnd);
}