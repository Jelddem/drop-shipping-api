package com.drop.shiping.api.drop_shiping_api.users.dtos;

import com.drop.shiping.api.drop_shiping_api.auth.validation.SizeConstraint;
import jakarta.validation.constraints.NotBlank;

public record PasswordDTO(
    @NotBlank(message = "{NotBlank.validation.text}")
    @SizeConstraint(min = 8, max = 255)
    String currentPassword,

    @NotBlank(message = "{NotBlank.validation.text}")
    @SizeConstraint(min = 8, max = 255)
    String newPassword
) {
}
