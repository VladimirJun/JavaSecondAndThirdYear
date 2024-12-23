package org.example.validator.request;


import org.example.model.StudentStatus;
import org.example.request.EditStudentRequest;
import org.example.utilities.Consts;
import org.example.validator.Validator;
import org.example.validator.primitive.EnumValidator;
import org.example.validator.primitive.LongValidator;
import org.example.validator.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EditStudentRequestValidator implements Validator<EditStudentRequest> {

    private final List<String> errors = new ArrayList<>();
    private static final Predicate<EditStudentRequest> IS_EMPTY_REQUEST = e ->
            e.lastName() == null
                    && e.firstName() == null
                    && e.patronymic() == null
                    && e.status() == null;

    @Override
    public List<String> getErrors(EditStudentRequest entity) {
        if (IS_EMPTY_REQUEST.test(entity)) {
            errors.add("Request has no payload");
            return errors;
        }
        LongValidator.isNonNegative(entity.id(), "id", errors);
        LongValidator.isPositive(entity.id(), "id", errors);

        if (entity.lastName() != null) {
            StringValidator.notBlank(entity.lastName(), "lastname", errors);
            StringValidator.maxLength(entity.lastName(), Consts.STRING_MAX_LENGTH, "lastname", errors);
        }

        if (entity.firstName() != null) {
            StringValidator.notBlank(entity.firstName(), "firstname", errors);
            StringValidator.maxLength(entity.firstName(), Consts.STRING_MAX_LENGTH, "firstname", errors);
        }

        if (entity.patronymic() != null) {
            StringValidator.notBlank(entity.patronymic(), "middle_name", errors);
            StringValidator.maxLength(entity.patronymic(), Consts.STRING_MAX_LENGTH, "middle_name", errors);
        }

        LongValidator.isNonNegative(entity.groupId(), "groupId", errors);
        LongValidator.isPositive(entity.groupId(), "groupId", errors);


        if (entity.status() != null) {
            EnumValidator.validate(entity.status(), StudentStatus.class, "status", errors);
        }

        return errors;
    }
}