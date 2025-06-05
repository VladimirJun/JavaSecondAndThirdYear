package org.example.demo1.service;

import org.example.demo1.dto.teacher.CreateTeacherDto;
import org.example.demo1.dto.teacher.TeacherDto;
import org.example.demo1.dto.teacher.UpdateTeacherDto;

import java.util.List;

public interface TeacherService {

    Long addTeacher(CreateTeacherDto teacherRequest);

    void editTeacher(Long id, UpdateTeacherDto teacherRequest);

    void deleteTeacher(Long id);

    TeacherDto getTeacherById(Long id);

    List<TeacherDto> getTeachers();
}