package com.example.antifraud.controllers;

import com.example.antifraud.models.dto.TransactionDto;
import com.example.antifraud.models.entities.Transaction;
import com.example.antifraud.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/api/antifraud/transaction")
    public ResponseEntity<?> transaction(@RequestBody Transaction transaction) {
        ResponseEntity<?> responseEntity = transactionService.processTransaction(transaction);
        return responseEntity;
    }

    @GetMapping("/api/antifraud/transaction/history")
    public ResponseEntity<?> historyAll(){
        ResponseEntity<?> responseEntity = transactionService.getHistoryAll();
        return responseEntity;
    }

    @GetMapping("/api/antifraud/transaction/history/{number}")
    public ResponseEntity<?> historyByNumber(@PathVariable String number) {
        ResponseEntity<?> responseEntity = transactionService.getHistoryByNumber(number);
        return responseEntity;
    }
}
