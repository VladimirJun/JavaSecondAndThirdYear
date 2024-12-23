package org.example.validator.request;


import org.example.request.GetLessonsByTeacherRequest;
import org.example.validator.Validator;
import org.example.validator.primitive.DateValidator;
import org.example.validator.primitive.LongValidator;
import org.example.validator.primitive.NotNullValidator;

import java.util.ArrayList;
import java.util.List;

public class GetLessonsByTeacherRequestValidator implements Validator<GetLessonsByTeacherRequest> {

    private final List<String> errors = new ArrayList<>();

    @Override
    public List<String> getErrors(GetLessonsByTeacherRequest entity) {
        if (NotNullValidator.notNull(entity.beginDate(), "beginDate", errors)) {
            DateValidator.validate(entity.beginDate(), "beginDate", errors);
        }

        if (NotNullValidator.notNull(entity.endDate(), "endDate", errors)) {
            DateValidator.validate(entity.endDate(), "endDate", errors);
        }

        if (NotNullValidator.notNull(entity.teacherId(), "teacherId", errors)) {
            LongValidator.isNonNegative(entity.teacherId(), "teacherId", errors);
            LongValidator.isPositive(entity.teacherId(), "teacherId", errors);
        }

        return errors;
    }
}