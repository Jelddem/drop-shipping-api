package com.drop.shiping.api.drop_shiping_api.products.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

public record CategoryResponseDTO(
    String id,
    String categoryName,
    @JsonIgnoreProperties("categories")
    List<ProductItemDTO> products,
    LocalDateTime createdAt
) {
}
