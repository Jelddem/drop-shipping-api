package com.drop.shiping.api.drop_shiping_api.products.services;

import com.drop.shiping.api.drop_shiping_api.products.dtos.VariantDTO;
import com.drop.shiping.api.drop_shiping_api.products.dtos.VariantResponseDTO;
import com.drop.shiping.api.drop_shiping_api.products.entities.Variant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VariantService {
    Page<VariantResponseDTO> findAll(Pageable pageable);
    VariantResponseDTO findOne(String id);
    VariantDTO addVariant(VariantDTO newVariant);
    Optional<VariantDTO> updateVariant(String id, VariantDTO variant);
    Optional<Variant> deleteVariant(String id);
    List<Variant> findVariantsByName(List<String> tag);
}
