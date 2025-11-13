package com.drop.shiping.api.drop_shiping_api.auth.mappers;

import com.drop.shiping.api.drop_shiping_api.auth.dtos.RegisterUserDTO;
import com.drop.shiping.api.drop_shiping_api.users.dtos.UserResponseDTO;
import com.drop.shiping.api.drop_shiping_api.users.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthMapper {
    AuthMapper MAPPER = Mappers.getMapper(AuthMapper.class);

    @Mapping(target = "admin", expression = "java(isAdmin)")
    RegisterUserDTO requestDTOtoNotAdmin(RegisterUserDTO user, boolean isAdmin);

    UserResponseDTO userToUserResponse(User user);
}
