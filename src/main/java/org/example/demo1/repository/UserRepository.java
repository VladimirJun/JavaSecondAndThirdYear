package org.example.demo1.repository;

import org.example.demo1.entity.user.Role;
import org.example.demo1.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByRoleAndProfileId(Role role, Long profileId);
}