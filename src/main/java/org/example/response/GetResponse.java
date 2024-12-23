package org.example.response;

import java.util.Objects;
//Параметризованный для того, чтобы была возможность делать гет для любой сущности
public record GetResponse<T> (T entity) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetResponse<?> that)) return false;
        return Objects.equals(entity, that.entity);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(entity);
    }

    @Override
    public String toString() {
        return "GetEntityRequestResponse{" +
                "entity=" + entity +
                '}';
    }
}