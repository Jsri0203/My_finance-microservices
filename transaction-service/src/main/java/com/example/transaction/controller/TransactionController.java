package com.example.transaction.controller;

import com.example.common.dto.TransactionEvent;
import com.example.transaction.entity.TransactionEntity;
import com.example.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @PostMapping
    public ResponseEntity<TransactionEntity> create(@RequestBody CreateTxnRequest req){
        TransactionEntity tx = service.createAndPublish(req.getAccountId(), req.getAmount(), req.getType());
        return ResponseEntity.ok(tx);
    }

    public static record CreateTxnRequest(UUID accountId, BigDecimal amount, String type){}
}
