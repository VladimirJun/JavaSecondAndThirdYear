package org.example.model;

import java.util.Objects;

public record SubjectModel(
        Long id,
        String name
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubjectModel subject)) return false;
        return Objects.equals(id, subject.id)
                && Objects.equals(name, subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}