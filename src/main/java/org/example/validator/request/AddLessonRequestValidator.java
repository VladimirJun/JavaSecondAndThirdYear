package org.example.validator.request;


import org.example.request.AddLessonRequest;
import org.example.validator.Validator;
import org.example.validator.primitive.DateValidator;
import org.example.validator.primitive.LongValidator;
import org.example.validator.primitive.NotNullValidator;
import org.example.validator.primitive.NumberValidator;

import java.util.ArrayList;
import java.util.List;

public class AddLessonRequestValidator implements Validator<AddLessonRequest> {

    private final List<String> errors = new ArrayList<>();

    @Override
    public List<String> getErrors(AddLessonRequest entity) {
        if (NotNullValidator.notNull(entity.subjectId(), "subjectId", errors)) {
            LongValidator.isPositive(entity.subjectId(), "subject_id", errors);
            LongValidator.isNonNegative(entity.subjectId(), "subject_id", errors);
        }

        if (NotNullValidator.notNull(entity.date(), "date", errors)) {
            DateValidator.validate(entity.date(), "date", errors);
        }
        NumberValidator.isNonNegative(entity.number(), "number", errors);
        NumberValidator.isPositive(entity.number(), "number", errors);

        if (NotNullValidator.notNull(entity.teacherId(), "teacherId", errors)) {
            LongValidator.isPositive(entity.teacherId(), "teacherId", errors);
            LongValidator.isNonNegative(entity.teacherId(), "teacherId", errors);

        }

        if (NotNullValidator.notNull(entity.groupId(), "groupId", errors)) {
            LongValidator.isPositive(entity.groupId(), "groupId", errors);
            LongValidator.isNonNegative(entity.groupId(), "groupId", errors);
        }

        return errors;
    }
}