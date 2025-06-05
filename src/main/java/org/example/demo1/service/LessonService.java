package org.example.demo1.service;




import org.example.demo1.dto.lesson.LessonDto;
import org.example.demo1.dto.lesson.UpdateLessonDto;

import java.time.LocalDate;
import java.util.List;

public interface LessonService {
    Long addLesson(LessonDto lessonRequest);

    void editLesson(Long id, UpdateLessonDto updateLessonDto);

    void deleteLessonsByTeacherId(Long teacherId);

    void deleteLessonsByGroupId(Long groupId);

    LessonDto getLessonById(Long lessonId);

    List<LessonDto> getLessonsByTeacherForPeriod(Long teacherId, LocalDate dateStart, LocalDate dateEnd);

    List<LessonDto> getLessonsByGroupForPeriod(Long groupId, LocalDate dateStart, LocalDate dateEnd);
}