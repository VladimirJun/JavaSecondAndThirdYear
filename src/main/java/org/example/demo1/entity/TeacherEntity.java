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
@Table(name = "t_teacher")
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private Long id;
    @Column(name = "c_name")
    private String name;
    @Column(name = "c_surname")
    private String surname;
    @Column(name = "c_patronymic")
    private String patronymic;

    @OneToMany(mappedBy = "teacher")
    private List<LessonEntity> lessons;

    public TeacherEntity() {
    }

    public TeacherEntity(Long id,
                   String name,
                   String surname,
                   String patronymic,
                   List<LessonEntity> lessons) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.lessons = lessons;
    }

}