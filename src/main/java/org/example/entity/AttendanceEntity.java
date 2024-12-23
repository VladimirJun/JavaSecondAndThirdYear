package org.example.entity;
import java.util.List;
import java.util.Objects;

public record AttendanceEntity(
        Long id,
        Long lessonId,
        List<Long> studentsId
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttendanceEntity that)) return false;
        return Objects.equals(id, that.id)
                && Objects.equals(lessonId, that.lessonId)
                && Objects.equals(studentsId, that.studentsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lessonId, studentsId);
    }

    @Override
    public String toString() {
        return "LessonReportEntity{" +
                "id='" + id + '\'' +
                ", lessonId='" + lessonId + '\'' +
                ", studentsId=" + studentsId +
                '}';
    }
}