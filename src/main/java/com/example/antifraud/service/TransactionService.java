package com.example.antifraud.service;

import com.example.antifraud.models.constants.Region;
import com.example.antifraud.models.dto.TransactionDto;
import com.example.antifraud.models.entities.Transaction;
import com.example.antifraud.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.Set;


@Service
public class TransactionService {


    private TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> processTransaction(Transaction transaction) {
        Set<String> ipSet = new HashSet<>();
        Set<Region> regionSet = new HashSet<>();
        repository.save(transaction);
        var transactions =
                repository.getTransactions(transaction.getNumber(), transaction.getDate().minusHours(1), transaction.getDate());
        var transactionList = transactions.get();
        for (Transaction t : transactionList) {
            ipSet.add(t.getIp());
            regionSet.add(t.getRegion());
        }

        if (ipSet.size() == 1 && regionSet.size() == 1) {
            return ResponseEntity.ok(new TransactionDto("ALLOWED", "none"));
        } else if (ipSet.size() == 2) {
            return ResponseEntity.ok(new TransactionDto("MANUAL_PROCESSING", "ip_correlation"));
        } else if (ipSet.size() > 2) {
            return ResponseEntity.ok(new TransactionDto("PROHIBITED", "ip_correlation"));
        } else if (regionSet.size() == 2) {
            return ResponseEntity.ok(new TransactionDto("MANUAL_PROCESSING", "region_correlation"));
        } else if (regionSet.size() > 2) {
            return ResponseEntity.ok(new TransactionDto("PROHIBITED", "region_correlation"));
        }

        return null;
    }

    public ResponseEntity<?> getHistoryAll() {
        List<Transaction> list = repository.getTransactionsAll();
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<?> getHistoryByNumber(String number) {
        List<Transaction> list = repository.getTransactionsByNumber(number);
        return ResponseEntity.ok(list);
    }
}