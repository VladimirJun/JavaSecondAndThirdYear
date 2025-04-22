package org.example.demo1.repository;

import org.example.demo1.entity.TeacherEntity;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<TeacherEntity, Long> {
}