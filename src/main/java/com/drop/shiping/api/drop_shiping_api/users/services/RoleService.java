package com.drop.shiping.api.drop_shiping_api.users.services;

import java.util.List;
import java.util.Optional;

import com.drop.shiping.api.drop_shiping_api.users.entities.Role;

public interface RoleService {
    List<Role> findAll();
    Optional<Role> findOne(String id);
}
