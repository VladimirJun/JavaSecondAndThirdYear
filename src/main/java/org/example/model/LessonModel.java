package org.example.model;

import java.time.LocalDate;
import java.util.Objects;

public record LessonModel(
        Long id,
        SubjectModel subject,
        LocalDate date,
        int number,
        TeacherModel teacher,
        GroupModel group,
        AttendanceModel report
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LessonModel lesson)) return false;
        return number == lesson.number
                && Objects.equals(id, lesson.id)
                && Objects.equals(subject, lesson.subject)
                && Objects.equals(date, lesson.date)
                && Objects.equals(teacher, lesson.teacher)
                && Objects.equals(group, lesson.group)
                && Objects.equals(report, lesson.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, date, number, teacher, group, report);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", date='" + date + '\'' +
                ", number=" + number +
                ", teacher=" + teacher +
                ", group=" + group +
                ", report=" + report +
                '}';
    }
}