package org.example.demo1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_student")

public class StudentEntity {
    @Id
    @Column(name = "c_student_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_surname")
    private String surname;

    @Column(name = "c_patronymic")
    private String patronymic;

    @Column(name = "c_status_of_students")
    private StudentStatus status;

    @ManyToOne()
    @JoinColumn(name = "c_group_id")
    private GroupEntity group;

    public StudentEntity() {
    }

    public StudentEntity(Long id) {
        this.id = id;
    }

    public StudentEntity(Long id,
                         String name,
                         String surname,
                         String patronymic,
                         StudentStatus status,
                         GroupEntity group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.status = status;
        this.group = group;
    }


    public StudentStatus getStatusOfStudents() {
        return status;
    }


    public void setStatusOfStudents(StudentStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
}