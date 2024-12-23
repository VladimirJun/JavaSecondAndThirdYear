package org.example.controller;


import org.example.exception.RepositoryException;
import org.example.exception.ServiceException;
import org.example.model.LessonModel;
import org.example.request.*;
import org.example.response.AddResponse;
import org.example.response.CommonResponse;
import org.example.response.GetResponse;
import org.example.service.LessonService;
import org.example.utilities.HTTPStatus;
import org.example.validator.IdRequestValidator;
import org.example.validator.request.AddLessonRequestValidator;
import org.example.validator.request.EditLessonRequestValidator;
import org.example.validator.request.GetLessonsByGroupRequestValidator;
import org.example.validator.request.GetLessonsByTeacherRequestValidator;

import java.util.Collection;
import java.util.List;

public class LessonController {

    private final LessonService service;

    public LessonController(LessonService service) {
        this.service = service;
    }

    // GET
    public CommonResponse<GetResponse<Collection<LessonModel>>> getLessonsByGroup(GetLessonsByGroupRequest request) {
        final List<String> errors = new GetLessonsByGroupRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            final Collection<LessonModel> lessons = service.getLessonsByGroup(request);
            return new CommonResponse<>(HTTPStatus.OK, new GetResponse<>(lessons));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // GET
    public CommonResponse<GetResponse<Collection<LessonModel>>> getLessonsByTeacher(GetLessonsByTeacherRequest request) {
        final List<String> errors = new GetLessonsByTeacherRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            final Collection<LessonModel> lessons = service.getLessonsByTeacher(request);
            return new CommonResponse<>(HTTPStatus.OK, new GetResponse<>(lessons));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // GET
    public CommonResponse<GetResponse<LessonModel>> getLessonById(IdRequest request) {
        final List<String> errors = new IdRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            final LessonModel lesson = service.getLessonById(request);
            return lesson == null ?
                    new CommonResponse<>(HTTPStatus.NOT_FOUND) :
                    new CommonResponse<>(HTTPStatus.OK, new GetResponse<>(lesson));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // POST
    public CommonResponse<AddResponse> addLesson(AddLessonRequest request) {
        final List<String> errors = new AddLessonRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            final long id = service.addLesson(request);
            return new CommonResponse<>(HTTPStatus.CREATED, new AddResponse(id));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // PATCH
    public CommonResponse<Void> editLesson(EditLessonRequest request) {
        final List<String> errors = new EditLessonRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            service.editLesson(request);
            return new CommonResponse<>(HTTPStatus.NO_CONTENT);
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // DELETE
    public CommonResponse<Void> deleteLesson(IdRequest request) {
        final List<String> errors = new IdRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            service.deleteLesson(request);
            return new CommonResponse<>(HTTPStatus.NO_CONTENT);
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }
}