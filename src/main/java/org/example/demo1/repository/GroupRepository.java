package org.example.demo1.repository;

import org.example.demo1.entity.GroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
}