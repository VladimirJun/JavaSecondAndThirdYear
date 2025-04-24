package org.example.demo1.service.impl;


import org.example.demo1.dto.SubjectDto;
import org.example.demo1.entity.SubjectEntity;
import org.example.demo1.exception.NotFoundException;
import org.example.demo1.mapper.SubjectMapper;
import org.example.demo1.repository.SubjectRepository;
import org.example.demo1.service.SubjectService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SubjectServiceImpl implements SubjectService {

    private static final String ENTITY = "Subject";

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    @Transactional
    public Long addSubject(SubjectDto subjectRequest) {
        try {

            SubjectEntity subject = this.subjectMapper.mapToEntity(subjectRequest);
            return this.subjectRepository.save(subject).getId();
        } catch (Exception e) {

            throw new ServiceException("Service error on add subject.", e);
        }
    }

    @Override
    @Transactional
    public void editSubject(SubjectDto subjectRequest) {
        try {

            if (!this.subjectRepository.existsById(subjectRequest.id())) {
                throw new NotFoundException(ENTITY, subjectRequest.id());
            }

            SubjectEntity subject = this.subjectMapper.mapToEntity(subjectRequest);
            this.subjectRepository.save(subject);
        } catch (Exception e) {

            throw new ServiceException("Service error on edit subject.", e);
        }
    }

    @Override
    @Transactional
    public void deleteSubject(Long id) {
        try {

            if (!this.subjectRepository.existsById(id)) {
                throw new NotFoundException(ENTITY, id);
            }
            this.subjectRepository.deleteById(id);
        } catch (Exception e) {

            throw new ServiceException("Service error on delete subject.", e);
        }
    }

    @Override
    public SubjectDto getSubjectById(Long id) {
        try {

            SubjectEntity subject = this.subjectRepository.findById(id).orElseThrow(
                    () -> new NotFoundException(ENTITY, id)
            );
            return this.subjectMapper.mapToDto(subject);
        } catch (Exception e) {

            throw new ServiceException("Service error on get subject.", e);
        }
    }
}