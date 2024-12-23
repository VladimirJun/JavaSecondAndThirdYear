package org.example.validator.primitive;

import java.util.List;

public class EnumValidator {

    public static <T extends Enum<T>> void validate(
            String input,
            Class<T> enumClass,
            String fieldName,
            List<String> collector
    ) {
        try {
            Enum.valueOf(enumClass, input);
        } catch (IllegalArgumentException ex) {
            collector.add(String.format("Field \"%s\" is not a value from enum %s", fieldName, enumClass.getSimpleName()));
        }
    }

}