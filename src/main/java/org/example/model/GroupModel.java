package org.example.model;

import java.util.Objects;

public record GroupModel(
        Long id,
        String name
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupModel studentGroup)) return false;
        return Objects.equals(id, studentGroup.id)
                && Objects.equals(name, studentGroup.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}