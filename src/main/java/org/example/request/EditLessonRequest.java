package org.example.request;

import java.util.List;
import java.util.Objects;

public record EditLessonRequest(
        Long id,
        Long subjectId,
        String date,
        Integer number,
        Long teacherId,
        Long groupId,
        List<Long> studentsId
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EditLessonRequest that)) return false;
        return Objects.equals(number, that.number)
                && Objects.equals(id, that.id)
                && Objects.equals(date, that.date)
                && Objects.equals(teacherId, that.teacherId)
                && Objects.equals(groupId, that.groupId)
                && Objects.equals(subjectId, that.subjectId)
                && Objects.equals(studentsId, that.studentsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subjectId, date, number, teacherId, groupId, studentsId);
    }

    @Override
    public String toString() {
        return "EditLessonRequest{" +
                "id='" + id + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", date='" + date + '\'' +
                ", number=" + number +
                ", teacherId='" + teacherId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", studentsId=" + studentsId +
                '}';
    }
}