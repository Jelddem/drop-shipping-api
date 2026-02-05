package com.drop.shiping.api.drop_shiping_api.products.dtos;

import com.drop.shiping.api.drop_shiping_api.common.validation.IfExists;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ProductCategoryDTO(
    @IfExists(message = "{IfExists.validation}", field = "categoryName", entity = "ProductCategory")
    @NotBlank(message = "{NotBlank.validation.text}")
    String categoryName,
    LocalDateTime createdAt
) {
}
