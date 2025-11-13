package com.drop.shiping.api.drop_shiping_api.auth.dtos;

import com.drop.shiping.api.drop_shiping_api.users.entities.User;

public record UserInfo(
    String identifier,
    User user
) {
}
