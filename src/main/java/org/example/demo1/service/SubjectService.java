package org.example.demo1.service;

import org.example.demo1.dto.subject.SubjectDto;
import org.example.demo1.dto.subject.UpdateSubjectDto;

import java.util.List;

public interface SubjectService {
    Long addSubject(SubjectDto subjectDto);

    void editSubject(Long id, UpdateSubjectDto updateSubjectDto);

    void deleteSubject(Long id);

    SubjectDto getSubjectById(Long id);

    List<SubjectDto> getAllSubjects();
}