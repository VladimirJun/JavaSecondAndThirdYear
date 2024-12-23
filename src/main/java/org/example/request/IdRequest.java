package org.example.request;

import java.util.Objects;

public record IdRequest(long id) {
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdRequest that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return "GetByIdRequest{" +
                "id='" + id + '\'' +
                '}';
    }
}