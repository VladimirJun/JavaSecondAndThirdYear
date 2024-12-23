package org.example.request;

import java.util.List;
import java.util.Objects;

public record AddLessonRequest(
        Long subjectId,
        String date,
        int number,
        Long teacherId,
        Long groupId,
        List<Long> studentsId
) {
    @Override
    public int hashCode() {
        return Objects.hash(subjectId, date, number, teacherId, groupId, studentsId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddLessonRequest that)) return false;
        return number == that.number
                && Objects.equals(subjectId, that.subjectId)
                && Objects.equals(date, that.date)
                && Objects.equals(teacherId, that.teacherId)
                && Objects.equals(groupId, that.groupId)
                && Objects.equals(studentsId, that.studentsId);
    }

    @Override
    public String toString() {
        return "AddLessonRequest{" +
                "subjectId='" + subjectId + '\'' +
                ", date=" + date +
                ", number=" + number +
                ", teacherId='" + teacherId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", studentsId='" + studentsId + '\'' +
                '}';
    }
}