package org.example.demo1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "t_groups")
public class GroupEntity {

    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_title")
    private String title;

    @OneToMany(mappedBy = "group")
    private List<StudentEntity> students;

    public GroupEntity() {
    }

    public GroupEntity(Long id) {
        this.id = id;
    }

    public GroupEntity(Long id, String title, List<StudentEntity> students) {
        this.id = id;
        this.title = title;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }
}