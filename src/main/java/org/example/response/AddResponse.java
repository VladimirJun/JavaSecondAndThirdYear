package org.example.response;

import java.util.Objects;

public record AddResponse(Long id) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddResponse that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AddRequestResponse{" +
                "id='" + id + '\'' +
                '}';
    }
}