package org.example.controller;


import org.example.exception.RepositoryException;
import org.example.exception.ServiceException;
import org.example.model.GroupModel;
import org.example.request.AddGroupRequest;
import org.example.request.EditGroupRequest;
import org.example.request.IdRequest;
import org.example.response.AddResponse;
import org.example.response.CommonResponse;
import org.example.response.GetResponse;
import org.example.service.GroupService;
import org.example.utilities.HTTPStatus;
import org.example.validator.IdRequestValidator;
import org.example.validator.request.AddGroupRequestValidator;
import org.example.validator.request.EditGroupRequestValidator;

import java.util.Collection;
import java.util.List;

public class GroupController {

    private final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    // GET
    public CommonResponse<GetResponse<Collection<GroupModel>>> getAllGroups() {
        try {
            final Collection<GroupModel> entities = service.getStudentGroups();
            return new CommonResponse<>(HTTPStatus.OK, new GetResponse<>(entities));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }
    public CommonResponse<GetResponse<GroupModel>> getGroupById(IdRequest request) {
        final List<String> errors = new IdRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            final GroupModel group = service.getStudentGroupById(request);
            return group == null ?
                    new CommonResponse<>(HTTPStatus.NOT_FOUND) :
                    new CommonResponse<>(HTTPStatus.OK, new GetResponse<>(group));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }


    // POST
    public CommonResponse<AddResponse> addGroup(AddGroupRequest request) {
        final List<String> errors = new AddGroupRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            final Long id = service.addStudentGroup(request);
            return new CommonResponse<>(HTTPStatus.CREATED, new AddResponse(id));
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // PATCH
    public CommonResponse<Void> editGroup(EditGroupRequest request) {
        final List<String> errors = new EditGroupRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            service.editStudentGroup(request);
            return new CommonResponse<>(HTTPStatus.NO_CONTENT);
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }

    // DELETE
    public CommonResponse<Void> deleteGroup(IdRequest request) {
        final List<String> errors = new IdRequestValidator().getErrors(request);
        if (!errors.isEmpty()) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, errors);
        }

        try {
            service.deleteStudentGroup(request);
            return new CommonResponse<>(HTTPStatus.NO_CONTENT);
        } catch (RepositoryException ex) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ServiceException ex) {
            return new CommonResponse<>(HTTPStatus.FAILED_DEPENDENCY, ex.getMessage());
        }
    }
}