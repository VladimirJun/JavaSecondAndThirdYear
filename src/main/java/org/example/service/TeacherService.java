package org.example.service;


import org.example.entity.TeacherEntity;
import org.example.exception.ServiceException;
import org.example.model.TeacherModel;
import org.example.repository.EntityRepository;
import org.example.request.AddTeacherRequest;
import org.example.request.EditTeacherRequest;
import org.example.request.IdRequest;

import java.util.Collection;
import java.util.Optional;

public class TeacherService {

    private final EntityRepository<TeacherEntity> repository;

    public TeacherService(EntityRepository<TeacherEntity> repository) {
        this.repository = repository;
    }

    public Collection<TeacherModel> getTeachers() {
        return repository.getAll()
                .stream()
                .map(e -> new TeacherModel(e.id(), e.firstName(), e.lastName(), e.patronymic()))
                .toList();
    }

    public TeacherModel getTeacherById(IdRequest request) {
        final TeacherEntity entity = repository.get(request.id());
        return entity == null ? null : new TeacherModel(
                entity.id(),
                entity.firstName(),
                entity.lastName(),
                entity.patronymic()
        );
    }

    public Long addTeacher(AddTeacherRequest request) {
        final TeacherEntity entity = new TeacherEntity(
                null,
                request.firstName(),
                request.lastName(),
                request.patronymic()
        );
        return repository.create(entity);
    }

    public void editTeacher(EditTeacherRequest request) {
        final TeacherEntity tutor = Optional.ofNullable(repository.get(request.id()))
                .orElseThrow(() -> new ServiceException("No teacher entity with id " + request.id() + " found"));

        final TeacherEntity entity = new TeacherEntity(
                tutor.id(),
                request.firstName() == null ? tutor.firstName() : request.firstName(),
                request.lastName() == null ? tutor.lastName() : request.lastName(),
                request.patronymic() == null ? tutor.patronymic() : request.patronymic()
        );

        repository.update(entity);
    }

    public void deleteTeacher(IdRequest request) {
        repository.delete(request.id());
    }
}