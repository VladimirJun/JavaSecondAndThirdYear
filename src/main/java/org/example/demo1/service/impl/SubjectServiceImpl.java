package org.example.demo1.service.impl;


import org.example.demo1.dto.subject.SubjectDto;
import org.example.demo1.dto.subject.UpdateSubjectDto;
import org.example.demo1.entity.SubjectEntity;
import org.example.demo1.exception.NotFoundException;
import org.example.demo1.mapper.SubjectMapper;
import org.example.demo1.repository.SubjectRepository;
import org.example.demo1.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private static final String ENTITY = "Subject";

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public Long addSubject(SubjectDto subjectDto) {
        SubjectEntity subject = this.subjectMapper.mapToEntity(subjectDto);
        return this.subjectRepository.save(subject).getId();
    }

    @Override
    public void editSubject(Long id, UpdateSubjectDto updateSubjectDto) {
        SubjectEntity subject = this.subjectRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ENTITY, id)
        );
        this.subjectMapper.updateEntityFromDto(updateSubjectDto, subject);

        this.subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Long id) {
        SubjectEntity subject = this.subjectRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ENTITY, id)
        );
        this.subjectRepository.delete(subject);
    }

    @Override
    @Transactional(readOnly = true)
    public SubjectDto getSubjectById(Long id) {
        SubjectEntity subject = this.subjectRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ENTITY, id)
        );
        return this.subjectMapper.mapToDto(subject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectDto> getAllSubjects() {
        return this.subjectMapper.mapToDtos((List<SubjectEntity>) this.subjectRepository.findAll());
    }
}