package com.drop.shiping.api.drop_shiping_api.transactions.mappers;

import com.drop.shiping.api.drop_shiping_api.transactions.dtos.NewTransactionDTO;
import com.drop.shiping.api.drop_shiping_api.transactions.dtos.TransactionResponseDTO;
import com.drop.shiping.api.drop_shiping_api.products.dtos.ProductResponseDTO;
import com.drop.shiping.api.drop_shiping_api.products.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.drop.shiping.api.drop_shiping_api.transactions.entities.Transaction;
import com.drop.shiping.api.drop_shiping_api.transactions.dtos.UpdateOrderDTO;

@Mapper
public interface TransactionMapper {
    TransactionMapper MAPPER = Mappers.getMapper(TransactionMapper.class);

    TransactionResponseDTO transactionToResponseDTO(Transaction dto);

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "categoriesList", ignore = true)
    @Mapping(target = "images", ignore = true)
    ProductResponseDTO map(Product product);

    @Mapping(target = "id", ignore = true)
    void toUpdateOrder(UpdateOrderDTO dto, @MappingTarget Transaction order);
}
