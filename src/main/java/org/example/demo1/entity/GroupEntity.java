package org.example.demo1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "t_group")
public class GroupEntity {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }

    public List<LessonEntity> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonEntity> lessons) {
        this.lessons = lessons;
    }

    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "c_title")
    private String title;

    @OneToMany(mappedBy = "group")
    private List<StudentEntity> students;

    @OneToMany(mappedBy = "group")
    private List<LessonEntity> lessons;

    public GroupEntity() {
    }

    public GroupEntity(Long id) {
        this.id = id;
    }

    public GroupEntity(Long id, String title, List<StudentEntity> students, List<LessonEntity> lessons) {
        this.id = id;
        this.title = title;
        this.students = students;
        this.lessons = lessons;
    }

}