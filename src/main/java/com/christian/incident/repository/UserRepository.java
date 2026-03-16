package com.christian.incident.repository;

import com.christian.incident.entity.User;
import com.christian.incident.entity.enums.Roles;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{
    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query("select u.role from User u where u.username like :username")
    Roles findRoleByUsername(String username);
}

