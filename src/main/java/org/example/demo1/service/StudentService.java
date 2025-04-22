package org.example.demo1.service;


import java.util.List;

public interface StudentService {

    Long addStudent(StudentDto studentRequest);

    void editStudent(StudentDto student);

    void deleteStudent(Long id);

    StudentDto getStudentById(Long id);

    List<StudentDto> getByGroupId(Long id);
}