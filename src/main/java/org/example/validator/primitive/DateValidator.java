package org.example.validator.primitive;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class DateValidator {

    private static final Predicate<String> DATE_REGEX =
            Pattern.compile("(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[0,12]).(19|20)\\d{2}").asPredicate();

    public static void validate(String input, String fieldName, List<String> collector) {
        if (!DATE_REGEX.test(input)) {
            collector.add(String.format("Date field \"%s\" is invalid DATA format", fieldName));
        }
    }

}