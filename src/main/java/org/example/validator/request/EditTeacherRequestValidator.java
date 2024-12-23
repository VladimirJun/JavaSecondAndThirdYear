package org.example.validator.request;


import org.example.request.EditTeacherRequest;
import org.example.utilities.Consts;
import org.example.validator.Validator;
import org.example.validator.primitive.LongValidator;
import org.example.validator.primitive.NotNullValidator;
import org.example.validator.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EditTeacherRequestValidator implements Validator<EditTeacherRequest> {

    private final List<String> errors = new ArrayList<>();
    private static final Predicate<EditTeacherRequest> IS_EMPTY_REQUEST = e ->
            e.lastName() == null
                    && e.firstName() == null
                    && e.patronymic() == null;

    @Override
    public List<String> getErrors(EditTeacherRequest entity) {
        if (NotNullValidator.notNull(entity.id(), "id", errors)) {
            LongValidator.isNonNegative(entity.id(), "id", errors);
            LongValidator.isPositive(entity.id(), "id", errors);
        }

        if (IS_EMPTY_REQUEST.test(entity)) {
            errors.add("Request has no payload");
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

        if (NotNullValidator.notNull(entity.patronymic(), "middle_name", errors)) {
            StringValidator.notBlank(entity.patronymic(), "middle_name", errors);
            StringValidator.maxLength(entity.patronymic(), Consts.STRING_MAX_LENGTH, "middle_name", errors);
        }

        return errors;
    }
}