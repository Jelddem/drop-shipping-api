package com.drop.shiping.api.drop_shiping_api.transactions.dtos;

import com.drop.shiping.api.drop_shiping_api.products.dtos.ProductResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record ItemResponseDTO(
    Integer quantity,
    @JsonIgnoreProperties({"id", "variants"})
    ProductResponseDTO product
) {}
