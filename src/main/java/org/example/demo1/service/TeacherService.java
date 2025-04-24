package org.example.demo1.service;



import org.example.demo1.dto.TeacherDto;

import java.util.List;

public interface TeacherService {

    Long addTeacher(TeacherDto teacherRequest);

    void editTeacher(TeacherDto teacherRequest);

    void deleteTeacher(Long id);

    TeacherDto getTeacherById(Long id);

    List<TeacherDto> getTeachers();
}