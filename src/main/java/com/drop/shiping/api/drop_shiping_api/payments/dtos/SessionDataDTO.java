package com.drop.shiping.api.drop_shiping_api.payments.dtos;

public record SessionDataDTO(
    String name,
    String description,
    String transactionId,
    Long amount
) {}
