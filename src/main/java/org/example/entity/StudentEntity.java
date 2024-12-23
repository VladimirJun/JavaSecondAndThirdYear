package org.example.entity;



import org.example.model.StudentStatus;

import java.util.Objects;

public record StudentEntity(
        Long id,
        String firstName,
        String lastName,
        String patronymic,
        StudentStatus status,
        Long groupId
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(patronymic, that.patronymic) && status == that.status && Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, patronymic, status, groupId);
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", status=" + status +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}