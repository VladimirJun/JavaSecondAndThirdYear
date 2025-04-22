package org.example.demo1.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum StudentStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    DROPPED_OUT("DROPPED_OUT");

    @JsonValue
    private final String value;

    StudentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @JsonCreator
    public static StudentStatus fromValue(String value) {
        for (StudentStatus status : StudentStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }

        throw new IllegalArgumentException("Invalid status: " + value);
    }
}