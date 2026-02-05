package com.drop.shiping.api.drop_shiping_api.users.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.drop.shiping.api.drop_shiping_api.users.dtos.PasswordDTO;
import com.drop.shiping.api.drop_shiping_api.users.dtos.UserDTO;
import com.drop.shiping.api.drop_shiping_api.users.dtos.UserResponseDTO;
import com.drop.shiping.api.drop_shiping_api.users.enums.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.drop.shiping.api.drop_shiping_api.auth.dtos.RegisterUserDTO;
import com.drop.shiping.api.drop_shiping_api.users.entities.User;

public interface UserService {
    Page<UserResponseDTO> findAll(Pageable pageable);

    Page<UserResponseDTO> findByRole(Pageable pageable, boolean isAdmin);

    Optional<UserResponseDTO> findOne(String id);

    RegisterUserDTO save(RegisterUserDTO user);

    Optional<UserDTO> update(String id, UserDTO user);

    Optional<User> updatePassword(String id, PasswordDTO passwordInfo);

    Optional<User> delete(String id);

    Long usersSize();

    Page<UserResponseDTO> userSearch(Pageable pageable, String name, boolean isAdmin, Boolean isEnabled, Field field);

    Map<String, Long> userStats(boolean isAdmin);

    List<UserResponseDTO> latestUsers();
}
