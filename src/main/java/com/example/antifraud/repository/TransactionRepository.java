package com.example.antifraud.repository;

import com.example.antifraud.models.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query("SELECT t from Transaction t WHERE t.number = :number AND t.date BETWEEN :before AND :after ORDER BY t.id")
    List<Transaction> getTransactions(String number, LocalDateTime before, LocalDateTime after);

    @Query("SELECT t from Transaction t")
    List<Transaction> getTransactionsAll();

    @Query("SELECT t from Transaction t WHERE t.number = :number")
    List<Transaction> getTransactionsByNumber(String number);
}
