package org.example.demo1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "t_subject")
public class SubjectEntity {
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "c_title")
    private String title;

    public SubjectEntity() {
    }

    public SubjectEntity(Long id, String title) {
        this.id = id;
        this.title = title;
    }

}