package org.example.service;


import org.example.entity.SubjectEntity;
import org.example.exception.ServiceException;
import org.example.model.SubjectModel;
import org.example.repository.EntityRepository;
import org.example.request.AddSubjectRequest;
import org.example.request.EditSubjectRequest;
import org.example.request.IdRequest;

import java.util.Collection;

public class SubjectService {

    private final EntityRepository<SubjectEntity> repository;

    public SubjectService(EntityRepository<SubjectEntity> repository) {
        this.repository = repository;
    }

    public Collection<SubjectModel> getSubjects() {
        return repository.getAll()
                .stream()
                .map(e -> new SubjectModel(e.id(), e.name()))
                .toList();
    }

    public SubjectModel getSubjectById(IdRequest request) {
        final SubjectEntity entity = repository.get(request.id());
        return entity == null ? null : new SubjectModel(entity.id(), entity.name());
    }

    public Long addSubject(AddSubjectRequest request) {
        final SubjectEntity entity = new SubjectEntity(null, request.name());
        return repository.create(entity);
    }

    public void editSubject(EditSubjectRequest request) {
        if (repository.get(request.id()) == null) {
            throw new ServiceException("No subject with id " + request.id() + " found");
        }

        final SubjectEntity entity = new SubjectEntity(request.id(), request.name());
        repository.update(entity);
    }

    public void deleteSubject(IdRequest request) {
        repository.delete(request.id());
    }
}