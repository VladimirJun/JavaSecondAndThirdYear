package org.example.controller;


import org.example.exception.RepositoryException;
import org.example.exception.ServiceException;
import org.example.model.StudentModel;
import org.example.request.AddStudentRequest;
import org.example.request.EditStudentRequest;
import org.example.request.IdRequest;
import org.example.response.AddResponse;
import org.example.response.CommonResponse;
import org.example.response.GetResponse;
import org.example.service.StudentService;
import org.example.utilities.HTTPStatus;
import org.example.validator.IdRequestValidator;
import org.example.validator.request.AddStudentRequestValidator;
import org.example.validator.request.EditStudentRequestValidator;

import java.util.Collection;
import java.util.List;

public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // GET
    public CommonResponse<GetResponse<Collection<StudentModel>>> getStudentsByGroup(IdRequest request) {
        final List<String> errors = new IdRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            final List<StudentModel> students = service.getStudentsByGroup(request);
            return new CommonResponse<>(HTTPStatus.OK, new GetResponse<>(students));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // GET
    public CommonResponse<GetResponse<StudentModel>> getStudentById(IdRequest request) {
        final List<String> errors = new IdRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            final StudentModel student = service.getStudentById(request);
            return student == null ?
                    new CommonResponse<>(HTTPStatus.NOT_FOUND) :
                    new CommonResponse<>(HTTPStatus.OK, new GetResponse<>(student));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // POST
    public CommonResponse<AddResponse> addStudent(AddStudentRequest request) {
        final List<String> errors = new AddStudentRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            final long id = service.addStudent(request);
            return new CommonResponse<>(HTTPStatus.CREATED, new AddResponse(id));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    // PATCH
    public CommonResponse<Void> editStudent(EditStudentRequest request) {
        final List<String> errors = new EditStudentRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            service.editStudent(request);
            return new CommonResponse<>(HTTPStatus.NO_CONTENT);
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    // DELETE
    public CommonResponse<Void> deleteStudent(IdRequest request) {
        final List<String> errors = new IdRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            service.deleteStudent(request);
            return new CommonResponse<>(HTTPStatus.NO_CONTENT);
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}