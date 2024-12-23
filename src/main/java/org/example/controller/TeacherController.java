package org.example.controller;


import org.example.exception.RepositoryException;
import org.example.exception.ServiceException;
import org.example.model.TeacherModel;
import org.example.request.AddTeacherRequest;
import org.example.request.EditTeacherRequest;
import org.example.request.IdRequest;
import org.example.response.AddResponse;
import org.example.response.CommonResponse;
import org.example.response.GetResponse;
import org.example.service.TeacherService;
import org.example.utilities.HTTPStatus;
import org.example.validator.IdRequestValidator;
import org.example.validator.request.AddTeacherRequestValidator;
import org.example.validator.request.EditTeacherRequestValidator;

import java.util.Collection;
import java.util.List;

public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    // GET
    public CommonResponse<GetResponse<Collection<TeacherModel>>> getTeachers() {
        try {
            final Collection<TeacherModel> tutors = service.getTeachers();
            return new CommonResponse<>(HTTPStatus.OK, new GetResponse<>(tutors));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // GET
    public CommonResponse<GetResponse<TeacherModel>> getTeacherById(IdRequest request) {
        final List<String> errors = new IdRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            final TeacherModel tutor = service.getTeacherById(request);
            return tutor == null ?
                    new CommonResponse<>(HTTPStatus.NOT_FOUND) :
                    new CommonResponse<>(HTTPStatus.OK, new GetResponse<>(tutor));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // POST
    public CommonResponse<AddResponse> addTeacher(AddTeacherRequest request) {
        final List<String> errors = new AddTeacherRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            final Long id = service.addTeacher(request);
            return new CommonResponse<>(HTTPStatus.CREATED, new AddResponse(id));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // PATCH
    public CommonResponse<Void> editTeacher(EditTeacherRequest request) {
        final List<String> errors = new EditTeacherRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            service.editTeacher(request);
            return new CommonResponse<>(HTTPStatus.NO_CONTENT);
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // DELETE
    public CommonResponse<Void> deleteTeacher(IdRequest request) {
        final List<String> errors = new IdRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            service.deleteTeacher(request);
            return new CommonResponse<>(HTTPStatus.NO_CONTENT);
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }
}