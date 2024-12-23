package org.example.validator;


import org.example.request.IdRequest;
import org.example.validator.primitive.LongValidator;

import java.util.ArrayList;
import java.util.List;

public class IdRequestValidator implements Validator<IdRequest> {

    private final List<String> errors = new ArrayList<>();

    @Override
    public List<String> getErrors(IdRequest entity) {
            LongValidator.isNonNegative(entity.id(), "id", errors);
            LongValidator.isPositive(entity.id(), "id", errors);
        return errors;
    }
}