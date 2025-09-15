package com.example.transaction.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {
    @Id
    private UUID id;
    private UUID accountId;
    private BigDecimal amount;
    private String type;
    private Instant timestamp;
    private String status; // CREATED, PUBLISHED
}
