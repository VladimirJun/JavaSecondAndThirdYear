package org.example.demo1.repository;

import org.example.demo1.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
}