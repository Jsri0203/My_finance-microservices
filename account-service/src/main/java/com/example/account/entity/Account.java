package com.example.account.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    private UUID id;
    private String ownerName;
    private String currency;
    private BigDecimal balance;
}
