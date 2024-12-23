package org.example.request;

import java.util.Objects;

public record EditTeacherRequest(
        Long id,
        String firstName,
        String lastName,
        String patronymic
) {
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, patronymic);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EditTeacherRequest that)) return false;
        return Objects.equals(id, that.id)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(patronymic, that.patronymic);
    }

    @Override
    public String toString() {
        return "EditTutorRequest{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}