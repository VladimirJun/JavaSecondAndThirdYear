package org.example.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static LocalDate parseDate(String value) {
        return LocalDate.parse(value, DATE_FORMATTER);
    }

}