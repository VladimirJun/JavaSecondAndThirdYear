package org.example.demo1.service;


import org.example.demo1.dto.SubjectDto;

public interface SubjectService {
    Long addSubject(SubjectDto subjectRequest);

    void editSubject(SubjectDto student);

    void deleteSubject(Long id);

    SubjectDto getSubjectById(Long id);

}