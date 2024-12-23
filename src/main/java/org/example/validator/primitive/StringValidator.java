package org.example.validator.primitive;

import java.util.List;

public class StringValidator {
    public static void notBlank(String input, String fieldName, List<String> collector) {
        if (input.isBlank()) {
            collector.add(String.format("Field \"%s\" is blank", fieldName));
        }
    }

    public static void maxLength(String input, int maxLength, String fieldName, List<String> collector) {
        if (input.length() > maxLength) {
            collector.add(String.format("Field \"%s\" is longer than max length (%d>%d)", fieldName, input.length(), maxLength));
        }
    }
}