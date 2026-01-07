package com.drop.shiping.api.drop_shiping_api.transactions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.drop.shiping.api.drop_shiping_api.transactions.entities.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
