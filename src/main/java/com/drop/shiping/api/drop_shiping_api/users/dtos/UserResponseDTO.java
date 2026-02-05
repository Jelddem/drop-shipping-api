package com.drop.shiping.api.drop_shiping_api.users.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.drop.shiping.api.drop_shiping_api.images.entities.Image;

import java.time.LocalDateTime;

public record UserResponseDTO(
    String id,
    String name,
    String secondName,
    String lastnames,
    Long phoneNumber,
    String gender,
    String email,
    boolean admin,
    boolean enabled,
    LocalDateTime createdAt
) {
}
