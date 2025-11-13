package com.drop.shiping.api.drop_shiping_api.users.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drop.shiping.api.drop_shiping_api.users.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(String role);
}
