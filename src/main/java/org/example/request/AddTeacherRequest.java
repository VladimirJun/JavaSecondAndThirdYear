package org.example.request;

import java.util.Objects;

public record AddTeacherRequest(
        String firstName,
        String lastName,
        String patronymic
) {
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymic);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddTeacherRequest that)) return false;
        return Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(patronymic, that.patronymic);
    }

    @Override
    public String toString() {
        return "AddTutorRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}