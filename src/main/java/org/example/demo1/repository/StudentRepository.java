package org.example.demo1.repository;

import org.example.demo1.entity.GroupEntity;
import org.example.demo1.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findStudentByGroup(GroupEntity group);
}