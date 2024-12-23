package org.example.validator.request;


import org.example.request.AddSubjectRequest;
import org.example.utilities.Consts;
import org.example.validator.Validator;
import org.example.validator.primitive.NotNullValidator;
import org.example.validator.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class AddSubjectRequestValidator implements Validator<AddSubjectRequest> {

    private final List<String> errors = new ArrayList<>();

    @Override
    public List<String> getErrors(AddSubjectRequest entity) {
        if (NotNullValidator.notNull(entity.name(), "name", errors)) {
            StringValidator.notBlank(entity.name(), "name", errors);
            StringValidator.maxLength(entity.name(), Consts.STRING_MAX_LENGTH, "name", errors);
        }
        return errors;
    }
}