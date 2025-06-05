package org.example.demo1.service;

import org.example.demo1.dto.student.CreateStudentDto;
import org.example.demo1.dto.student.StudentDto;
import org.example.demo1.dto.student.UpdateStudentDto;

import java.util.List;

public interface StudentService {

    Long addStudent(CreateStudentDto studentRequest);

    void editStudent(Long id, UpdateStudentDto student);

    void deleteStudent(Long id);

    StudentDto getStudentById(Long id);

    List<StudentDto> getByGroupId(Long id);
}