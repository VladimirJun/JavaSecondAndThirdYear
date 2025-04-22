package org.example.demo1.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name = "t_lesson")
@Getter
@Setter
public class LessonEntity {
    @Id
    @Column(name = "c_lesson_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "c_teacher_id")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "c_group_id")
    private GroupEntity group;

    @Column(name = "c_date")
    private LocalDate date;

    @Column(name = "c_number_lesson")
    private int numberOfLesson;

    @ElementCollection
    @CollectionTable(name = "lesson_attendance", joinColumns = @JoinColumn(name = "c_lesson_id"))
    @MapKeyJoinColumn(name = "c_student_id")
    @Column(name = "c_attendance")
    private Map<StudentEntity, Boolean> studentAttendance;

    public LessonEntity() {
    }

    public LessonEntity(Long id,
                        TeacherEntity teacher,
                        GroupEntity group,
                        LocalDate date,
                        int numberOfLesson,
                        Map<StudentEntity, Boolean> studentAttendance) {
        this.id = id;
        this.teacher = teacher;
        this.group = group;
        this.date = date;
        this.numberOfLesson = numberOfLesson;
        this.studentAttendance = studentAttendance;
    }

}