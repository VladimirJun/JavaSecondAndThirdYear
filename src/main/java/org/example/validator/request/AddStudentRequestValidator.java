package org.example.validator.request;


import org.example.model.StudentStatus;
import org.example.request.AddStudentRequest;
import org.example.utilities.Consts;
import org.example.validator.Validator;
import org.example.validator.primitive.EnumValidator;
import org.example.validator.primitive.LongValidator;
import org.example.validator.primitive.NotNullValidator;
import org.example.validator.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class AddStudentRequestValidator implements Validator<AddStudentRequest> {

    private final List<String> errors = new ArrayList<>();

    @Override
    public List<String> getErrors(AddStudentRequest entity) {
        if (entity == null) {
            errors.add("Request body is empty");
            return errors;
        }

        if (NotNullValidator.notNull(entity.lastName(), "last_name", errors)) {
            StringValidator.notBlank(entity.lastName(), "last_name", errors);
            StringValidator.maxLength(entity.lastName(), Consts.STRING_MAX_LENGTH, "last_name", errors);
        }

        if (NotNullValidator.notNull(entity.firstName(), "first_name", errors)) {
            StringValidator.notBlank(entity.firstName(), "first_name", errors);
            StringValidator.maxLength(entity.firstName(), Consts.STRING_MAX_LENGTH, "first_name", errors);
        }

        if (NotNullValidator.notNull(entity.patronymic(), "patronymic", errors)) {
            StringValidator.notBlank(entity.patronymic(), "patronymic", errors);
            StringValidator.maxLength(entity.patronymic(), Consts.STRING_MAX_LENGTH, "patronymic", errors);
        }

        if (NotNullValidator.notNull(entity.groupId(), "group_id", errors)) {
            LongValidator.isPositive(entity.groupId(), "groupId", errors);
            LongValidator.isNonNegative(entity.groupId(), "groupId", errors);
        }

        if (NotNullValidator.notNull(entity.status(), "status", errors)) {
            EnumValidator.validate(entity.status(), StudentStatus.class, "status", errors);
        }

        return errors;
    }
}