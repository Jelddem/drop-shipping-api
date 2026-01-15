package com.drop.shiping.api.drop_shiping_api.transactions.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.drop.shiping.api.drop_shiping_api.transactions.dtos.TransactionResponseDTO;
import com.drop.shiping.api.drop_shiping_api.transactions.dtos.UserInfoDTO;
import com.drop.shiping.api.drop_shiping_api.transactions.entities.Transaction;
import com.drop.shiping.api.drop_shiping_api.transactions.dtos.NewTransactionDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    Page<TransactionResponseDTO> findAllByUser(String userRef, String userId, Pageable pageable);

    Optional<TransactionResponseDTO> findOne(String id);

    String createTransaction(NewTransactionDTO dto);

    Map<String, String> addUserInfo(String userReference, HttpServletResponse response, String id, UserInfoDTO dto, String token);

    String updateProducts(String id, List<String> productIds);

    Optional<Transaction> delete(String id);
}
