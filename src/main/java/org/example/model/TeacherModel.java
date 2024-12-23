package org.example.model;

import java.util.Objects;

public record TeacherModel(
        Long id,
        String firstName,
        String lastName,
        String patronymic
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherModel teacher)) return false;
        return Objects.equals(id, teacher.id)
                && Objects.equals(firstName, teacher.firstName)
                && Objects.equals(lastName, teacher.lastName)
                && Objects.equals(patronymic, teacher.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, patronymic);
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}