package com.drop.shiping.api.drop_shiping_api.transactions.dtos;

import jakarta.validation.constraints.Email;

public record UserInfoDTO(
    String token,
    String userReference,
    String userNames,

    @Email
    String userEmail,
    Long userNumber,
    String userAddress
) {}
