package org.example.request;

import java.util.Objects;

public record EditSubjectRequest(
        Long id,
        String name
) {
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EditSubjectRequest that)) return false;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "EditSubjectRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}