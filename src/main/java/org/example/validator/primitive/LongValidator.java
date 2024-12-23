package org.example.validator.primitive;

import java.util.List;

public class LongValidator {

    public static void isPositive(long input, String fieldName, List<String> collector) {
        if (input <= 0) {
            collector.add(String.format("Field \"%s\" contains negative or zero value", fieldName));
        }
    }

    public static void isNonNegative(long input, String fieldName, List<String> collector) {
        if (input < 0) {
            collector.add(String.format("Field \"%s\" contains negative value", fieldName));
        }
    }
}