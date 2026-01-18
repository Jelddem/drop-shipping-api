package com.drop.shiping.api.drop_shiping_api.auth.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.drop.shiping.api.drop_shiping_api.common.validation.IfExists;
import com.drop.shiping.api.drop_shiping_api.common.validation.ImageFormat;
import com.drop.shiping.api.drop_shiping_api.common.validation.NotEmptyFile;
import com.drop.shiping.api.drop_shiping_api.images.entities.Image;
import com.drop.shiping.api.drop_shiping_api.users.entities.Role;
import com.drop.shiping.api.drop_shiping_api.auth.validation.SizeConstraint;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record RegisterUserDTO(
    @NotBlank(message = "{NotBlank.validation.text}")
    String name,

    String secondName,
    String lastnames,

    @IfExists(message = "{IfExists.validation}", field = "phoneNumber", entity = "User")
    Long phoneNumber,
    String gender,

    @Email
    @IfExists(message = "{IfExists.validation}", field ="email", entity = "User")
    @NotBlank(message = "{NotBlank.validation.text}")
    String email,

    @NotBlank(message = "{NotBlank.validation.text}")
    @SizeConstraint(min = 8, max = 255)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password,

    @JsonIgnoreProperties({"users", "id"})
    List<Role> roles,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    boolean admin
) {
}
