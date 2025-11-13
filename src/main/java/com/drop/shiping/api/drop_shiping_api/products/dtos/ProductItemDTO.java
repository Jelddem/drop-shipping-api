package com.drop.shiping.api.drop_shiping_api.products.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.drop.shiping.api.drop_shiping_api.images.entities.Image;

public record ProductItemDTO(
        String id,
        String productName,
        Long price,

        @JsonIgnoreProperties("id")
        Image mainImage
) {
}
