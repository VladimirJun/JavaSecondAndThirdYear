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

import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name = "t_lessons")
public class LessonEntity {
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @CollectionTable(name = "t_lesson_attendance", joinColumns = @JoinColumn(name = "c_lesson_id"))
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

    public Long getId() {
        return id;
    }


    public TeacherEntity getTeacher() {
        return teacher;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getNumberOfLesson() {
        return numberOfLesson;
    }

    public Map<StudentEntity, Boolean> getStudentAttendance() {
        return studentAttendance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setNumberOfLesson(int numberOfLesson) {
        this.numberOfLesson = numberOfLesson;
    }

    public void setStudentAttendance(Map<StudentEntity, Boolean> studentAttendance) {
        this.studentAttendance = studentAttendance;
    }
}