package org.example.entity;

import java.time.LocalDate;
import java.util.Objects;

public record LessonEntity(
        Long id,
        Long subjectId,
        LocalDate date,
        int number,
        Long teacherId,
        Long groupId
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LessonEntity(
                Long id1, Long subjectId1, LocalDate date1, int number1, Long teacherId1, Long groupId1
        ))) return false;
        return number == number1
                && Objects.equals(id, id1)
                && Objects.equals(subjectId, subjectId1)
                && Objects.equals(date, date1)
                && Objects.equals(teacherId, teacherId1)
                && Objects.equals(groupId, groupId1);
    }

    @Override
    public String toString() {
        return "LessonEntity{" +
                "id='" + id + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", date=" + date +
                ", number=" + number +
                ", teacherId='" + teacherId + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}