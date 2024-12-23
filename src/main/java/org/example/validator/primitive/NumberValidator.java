package org.example.validator.primitive;

import java.util.List;

public class NumberValidator {

    public static void isPositive(int input, String fieldName, List<String> collector) {
        if (input <= 0) {
            collector.add(String.format("Field \"%s\" contains negative or zero value", fieldName));
        }
    }

    public static void isNonNegative(int input, String fieldName, List<String> collector) {
        if (input < 0) {
            collector.add(String.format("Field \"%s\" contains negative value", fieldName));
        }
    }
}