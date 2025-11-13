package com.drop.shiping.api.drop_shiping_api.products.mappers;

import com.drop.shiping.api.drop_shiping_api.products.dtos.VariantDTO;
import com.drop.shiping.api.drop_shiping_api.products.dtos.VariantResponseDTO;
import com.drop.shiping.api.drop_shiping_api.products.entities.Variant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VariantMapper {
    VariantMapper MAPPER = Mappers.getMapper(VariantMapper.class);

    @Mapping(target = "values", expression = "java(listValues)")
    Variant dtoToVariant(VariantDTO variantDTO, String listValues);

    @Mapping(target = "listValues", expression = "java(values)")
    VariantResponseDTO variantToDTO(Variant variant, List<String> values);
}
