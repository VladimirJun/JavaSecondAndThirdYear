package org.example.service;


import org.example.entity.GroupEntity;
import org.example.exception.ServiceException;
import org.example.model.GroupModel;
import org.example.repository.EntityRepository;
import org.example.request.AddGroupRequest;
import org.example.request.EditGroupRequest;
import org.example.request.IdRequest;

import java.util.Collection;

public class GroupService {

    private final EntityRepository<GroupEntity> repository;

    public GroupService(EntityRepository<GroupEntity> repository) {
        this.repository = repository;
    }

    public Collection<GroupModel> getStudentGroups() {
        return repository.getAll()
                .stream()
                .map(e -> new GroupModel(e.id(), e.name()))
                .toList();
    }

    public GroupModel getStudentGroupById(IdRequest request) {
        final GroupEntity entity = repository.get(request.id());
        return entity == null ? null :
                new GroupModel(entity.id(), entity.name());
    }

    public Long addStudentGroup(AddGroupRequest request) {
        final GroupEntity entity = new GroupEntity(null, request.name());
        return repository.create(entity);
    }

    public void editStudentGroup(EditGroupRequest request) {
        if (repository.get(request.id()) == null) {
            throw new ServiceException("No group entity with id " + request.id() + " found");
        }

        final GroupEntity entity = new GroupEntity(request.id(), request.name());
        repository.update(entity);
    }

    public void deleteStudentGroup(IdRequest request) {
        repository.delete(request.id());
    }
}