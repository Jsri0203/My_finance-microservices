package com.example.common.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEvent {
    private UUID transactionId;
    private UUID accountId;
    private BigDecimal amount;
    private String type; // DEBIT or CREDIT
    private Instant timestamp;
}
