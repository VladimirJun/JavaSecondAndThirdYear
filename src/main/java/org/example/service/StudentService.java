package org.example.service;


import org.example.entity.GroupEntity;
import org.example.entity.StudentEntity;
import org.example.exception.ServiceException;
import org.example.model.GroupModel;
import org.example.model.StudentModel;
import org.example.model.StudentStatus;
import org.example.repository.EntityRepository;
import org.example.request.AddStudentRequest;
import org.example.request.EditStudentRequest;
import org.example.request.IdRequest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StudentService {

    private final EntityRepository<StudentEntity> studentRepository;
    private final EntityRepository<GroupEntity> groupRepository;

    public StudentService(
            EntityRepository<StudentEntity> studentRepository,
            EntityRepository<GroupEntity> groupRepository
    ) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public List<StudentModel> getStudentsByGroup(IdRequest request) {
        return studentRepository.getAll()
                .stream()
                .filter(e -> Objects.equals(e.groupId(), request.id()))
                .map(this::constructModel)
                .toList();
    }

    public StudentModel getStudentById(IdRequest request) {
        final StudentEntity entity = studentRepository.get(request.id());
        return entity == null ? null : constructModel(entity);
    }

    public Long addStudent(AddStudentRequest request) {
        final StudentEntity entity = new StudentEntity(
                null,
                request.firstName(),
                request.lastName(),
                request.patronymic(),
                StudentStatus.valueOf(request.status()),
                request.groupId()
        );

        return studentRepository.create(entity);
    }

    public void editStudent(EditStudentRequest request) {
        final StudentEntity student = Optional.ofNullable(studentRepository.get(request.id()))
                .orElseThrow(() -> new ServiceException("No student entity with id " + request.id() + " found"));

        final StudentEntity entity = new StudentEntity(
                student.id(),
                request.firstName() == null ? student.firstName() : request.firstName(),
                request.lastName() == null ? student.lastName() : request.lastName(),
                request.lastName() == null ? student.patronymic() : request.patronymic(),
                request.status() == null ? student.status() : StudentStatus.valueOf(request.status()),
                request.groupId() == null ? student.id() : request.groupId()
        );

        studentRepository.update(entity);
    }

    public void deleteStudent(IdRequest request) {
        studentRepository.delete(request.id());
    }

    private StudentModel constructModel(StudentEntity entity) {
        final GroupModel group = Optional.ofNullable(groupRepository.get(entity.groupId()))
                .map(e -> new GroupModel(e.id(), e.name()))
                .orElseThrow(() -> new ServiceException("No group entity with id " + entity.groupId() + " found"));

        return new StudentModel(
                entity.id(),
                entity.firstName(),
                entity.lastName(),
                entity.patronymic(),
                entity.status(),
                group
        );
    }
}