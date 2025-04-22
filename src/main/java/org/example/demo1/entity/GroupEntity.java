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

@Setter
@Getter
@Entity
@Table(name = "t_group")
public class GroupEntity {

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