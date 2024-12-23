package org.example.validator.request;


import org.example.request.AddTeacherRequest;
import org.example.utilities.Consts;
import org.example.validator.Validator;
import org.example.validator.primitive.NotNullValidator;
import org.example.validator.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class AddTeacherRequestValidator implements Validator<AddTeacherRequest> {

    private final List<String> errors = new ArrayList<>();

    @Override
    public List<String> getErrors(AddTeacherRequest entity) {
        if (NotNullValidator.notNull(entity.lastName(), "lastname", errors)) {
            StringValidator.notBlank(entity.lastName(), "lastname", errors);
            StringValidator.maxLength(entity.lastName(), Consts.STRING_MAX_LENGTH, "lastname", errors);
        }

        if (NotNullValidator.notNull(entity.firstName(), "firstname", errors)) {
            StringValidator.notBlank(entity.firstName(), "firstname", errors);
            StringValidator.maxLength(entity.firstName(), Consts.STRING_MAX_LENGTH, "firstname", errors);
        }

        if (NotNullValidator.notNull(entity.patronymic(), "patronymic", errors)) {
            StringValidator.notBlank(entity.patronymic(), "patronymic", errors);
            StringValidator.maxLength(entity.patronymic(), Consts.STRING_MAX_LENGTH, "patronymic", errors);
        }

        return errors;
    }
}