package org.example.validator.request;


import org.example.request.EditGroupRequest;
import org.example.utilities.Consts;
import org.example.validator.Validator;
import org.example.validator.primitive.LongValidator;
import org.example.validator.primitive.NotNullValidator;
import org.example.validator.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class EditGroupRequestValidator implements Validator<EditGroupRequest> {

    private final List<String> errors = new ArrayList<>();

    @Override
    public List<String> getErrors(EditGroupRequest entity) {
        if (NotNullValidator.notNull(entity.id(), "id", errors)) {
            LongValidator.isNonNegative(entity.id(), "id", errors);
            LongValidator.isPositive(entity.id(), "id", errors);
        }

        if (entity.name() != null) {
            StringValidator.notBlank(entity.name(), "name", errors);
            StringValidator.maxLength(entity.name(), Consts.STRING_MAX_LENGTH, "name", errors);
        } else {
            errors.add("Request has no payload");
        }

        return errors;
    }
}