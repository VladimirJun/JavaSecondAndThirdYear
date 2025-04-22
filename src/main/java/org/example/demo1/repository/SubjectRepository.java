package org.example.demo1.repository;

import org.example.demo1.entity.SubjectEntity;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<SubjectEntity, Long> {
}