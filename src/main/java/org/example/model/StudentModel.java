package org.example.model;

import java.util.Objects;

public record StudentModel(
        Long id,
        String firstName,
        String lastName,
        String patronymic,
        StudentStatus status,
        GroupModel group
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentModel student)) return false;
        return Objects.equals(id, student.id)
                && Objects.equals(lastName, student.lastName)
                && Objects.equals(firstName, student.firstName)
                && Objects.equals(patronymic, student.patronymic)
                && Objects.equals(group, student.group)
                && status == student.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, patronymic, status, group);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", status=" + status +
                ", group=" + group +
                '}';
    }
}