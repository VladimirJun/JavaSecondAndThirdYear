package org.example.model;

import java.util.List;
import java.util.Objects;

public record AttendanceModel(
        Long id,
        Long lessonId,
        List<StudentModel> students
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttendanceModel that)) return false;
        return Objects.equals(id, that.id)
                && Objects.equals(lessonId, that.lessonId)
                && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lessonId, students);
    }

    @Override
    public String toString() {
        return "LessonReport{" +
                "id='" + id + '\'' +
                ", lessonId=" + lessonId +
                ", students=" + students +
                '}';
    }
}