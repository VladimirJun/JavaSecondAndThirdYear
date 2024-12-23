package org.example.request;

import java.util.Objects;

public record EditStudentRequest(
        Long id,
        String lastName,
        String firstName,
        String patronymic,
        Long groupId,
        String status
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EditStudentRequest that)) return false;
        return Objects.equals(id, that.id)
                && Objects.equals(status, that.status)
                && Objects.equals(groupId, that.groupId)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(patronymic, that.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, patronymic, groupId, status);
    }

    @Override
    public String toString() {
        return "EditStudentRequest{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", groupId='" + groupId + '\'' +
                ", status=" + status +
                '}';
    }
}