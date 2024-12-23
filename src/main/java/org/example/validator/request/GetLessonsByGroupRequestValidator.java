package org.example.validator.request;


import org.example.request.GetLessonsByGroupRequest;
import org.example.validator.Validator;
import org.example.validator.primitive.DateValidator;
import org.example.validator.primitive.LongValidator;
import org.example.validator.primitive.NotNullValidator;

import java.util.ArrayList;
import java.util.List;

public class GetLessonsByGroupRequestValidator implements Validator<GetLessonsByGroupRequest> {

    private final List<String> errors = new ArrayList<>();

    @Override
    public List<String> getErrors(GetLessonsByGroupRequest entity) {
        if (NotNullValidator.notNull(entity.beginDate(), "beginDate", errors)) {
            DateValidator.validate(entity.beginDate(), "beginDate", errors);
        }

        if (NotNullValidator.notNull(entity.endDate(), "endDate", errors)) {
            DateValidator.validate(entity.endDate(), "endDate", errors);
        }

        if (NotNullValidator.notNull(entity.groupId(), "groupId", errors)) {
            LongValidator.isNonNegative(entity.groupId(), "groupId", errors);
            LongValidator.isPositive(entity.groupId(), "groupId", errors);
        }

        return errors;
    }
}