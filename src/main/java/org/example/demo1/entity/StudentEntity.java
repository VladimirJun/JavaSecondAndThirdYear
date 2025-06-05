package org.example.demo1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.example.demo1.entity.user.UserProfileReference;


@Entity
@Table(name = "t_students")
public class StudentEntity implements UserProfileReference {
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_surname")
    private String surname;

    @Column(name = "c_patronymic")
    private String patronymic;

    @Enumerated(EnumType.STRING)
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public StudentStatus getStatusOfStudents() {
        return status;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setStatusOfStudents(StudentStatus status) {
        this.status = status;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
}