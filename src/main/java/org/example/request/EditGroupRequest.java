package org.example.request;

import java.util.Objects;

public record EditGroupRequest(Long id, String name) {
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EditGroupRequest that)) return false;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "EditGroup{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}