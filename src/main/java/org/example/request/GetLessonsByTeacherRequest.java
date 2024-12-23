package org.example.request;

import java.util.Objects;

public record GetLessonsByTeacherRequest(
        String beginDate,
        String endDate,
        Long teacherId
) {
    @Override
    public int hashCode() {
        return Objects.hash(beginDate, endDate, teacherId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetLessonsByTeacherRequest that)) return false;
        return Objects.equals(beginDate, that.beginDate)
                && Objects.equals(endDate, that.endDate)
                && Objects.equals(teacherId, that.teacherId);
    }

    @Override
    public String toString() {
        return "GetLessonsByTutor{" +
                "beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }
}