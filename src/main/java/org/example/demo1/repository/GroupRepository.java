package org.example.demo1.repository;

import org.example.demo1.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.Nullable;


import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    @Query("SELECT g FROM GroupEntity g LEFT JOIN FETCH g.students where g.id = :id")
    Optional<GroupEntity> findByIdWithStudents(@Param("id") Long id);

    @Query("SELECT g FROM GroupEntity g LEFT JOIN FETCH g.students")
    List<GroupEntity> findAllWithStudents();
}