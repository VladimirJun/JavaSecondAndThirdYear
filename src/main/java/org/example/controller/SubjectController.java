package org.example.controller;


import org.example.exception.RepositoryException;
import org.example.exception.ServiceException;
import org.example.model.SubjectModel;
import org.example.request.AddSubjectRequest;
import org.example.request.EditSubjectRequest;
import org.example.request.IdRequest;
import org.example.response.AddResponse;
import org.example.response.CommonResponse;
import org.example.response.GetResponse;
import org.example.service.SubjectService;
import org.example.utilities.HTTPStatus;
import org.example.validator.IdRequestValidator;
import org.example.validator.request.AddSubjectRequestValidator;
import org.example.validator.request.EditSubjectRequestValidator;

import java.util.Collection;
import java.util.List;

public class SubjectController {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }


    // GET
    public CommonResponse<GetResponse<Collection<SubjectModel>>> getSubjects() {
        try {
            final Collection<SubjectModel> subjects = service.getSubjects();
            return new CommonResponse<>(HTTPStatus.OK, new GetResponse<>(subjects));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // GET
    public CommonResponse<GetResponse<SubjectModel>> getSubjectById(IdRequest request) {
        final List<String> errors = new IdRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            final SubjectModel subject = service.getSubjectById(request);
            return subject == null ?
                    new CommonResponse<>(HTTPStatus.NOT_FOUND) :
                    new CommonResponse<>(HTTPStatus.OK, new GetResponse<>(subject));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // POST
    public CommonResponse<AddResponse> addSubject(AddSubjectRequest request) {
        final List<String> errors = new AddSubjectRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            final long id = service.addSubject(request);
            return new CommonResponse<>(HTTPStatus.CREATED, new AddResponse(id));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // PATCH
    public CommonResponse<Void> editSubject(EditSubjectRequest request) {
        final List<String> errors = new EditSubjectRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            service.editSubject(request);
            return new CommonResponse<>(HTTPStatus.NO_CONTENT);
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // DELETE
    public CommonResponse<Void> deleteSubject(IdRequest request) {
        final List<String> errors = new IdRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            service.deleteSubject(request);
            return new CommonResponse<>(HTTPStatus.NO_CONTENT);
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }
}