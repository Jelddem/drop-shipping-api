package com.drop.shiping.api.drop_shiping_api.transactions.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.drop.shiping.api.drop_shiping_api.transactions.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Page<Transaction> findTransactionsByUser_IdOrUserReference(String userId, String userReference, Pageable pageable);
}
