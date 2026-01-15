package com.drop.shiping.api.drop_shiping_api.auth.services;

import com.drop.shiping.api.drop_shiping_api.users.entities.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> getUser(String token);
}
