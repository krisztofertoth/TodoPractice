package com.todo.demo.repository;

import java.util.Optional;

import com.todo.demo.models.ERole;
import com.todo.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
