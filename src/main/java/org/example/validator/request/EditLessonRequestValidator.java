package org.example.validator.request;


import org.example.request.EditLessonRequest;
import org.example.validator.Validator;
import org.example.validator.primitive.DateValidator;
import org.example.validator.primitive.NumberValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EditLessonRequestValidator implements Validator<EditLessonRequest> {

    private final List<String> errors = new ArrayList<>();
    private static final Predicate<EditLessonRequest> IS_EMPTY_REQUEST = e ->
            e.subjectId() == null
                    && e.date() == null
                    && e.number() == null
                    && e.teacherId() == null
                    && e.groupId() == null;

    @Override
public List<String> getErrors(EditLessonRequest entity) {
    if (IS_EMPTY_REQUEST.test(entity)) {
        errors.add("Request has no payload");
        return errors;
    }
    if (entity.date() != null) {
        DateValidator.validate(entity.date(), "date", errors);
    }

    if (entity.number() != null) {
        NumberValidator.isPositive(entity.number(), "number", errors);
    }
    return errors;
}
}