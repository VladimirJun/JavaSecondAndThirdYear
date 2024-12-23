package org.example.request;

import java.util.Objects;

public record AddGroupRequest(String name) {
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddGroupRequest that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "AddStudentGroup{" +
                "name='" + name + '\'' +
                '}';
    }
}