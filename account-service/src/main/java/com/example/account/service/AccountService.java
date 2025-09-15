package com.example.account.service;

import com.example.account.entity.Account;
import com.example.account.repository.AccountRepository;
import com.example.common.dto.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repo;

    public Optional<Account> getAccount(UUID id){ return repo.findById(id); }
    public Account createAccount(Account a){
        if (a.getId() == null) a.setId(UUID.randomUUID());
        if (a.getBalance() == null) a.setBalance(BigDecimal.ZERO);
        return repo.save(a);
    }

    // Kafka listener to update balances when transaction occurs
    @KafkaListener(topics = "transactions", groupId = "account-group")
    @Transactional
    public void processTransaction(TransactionEvent event) {
        repo.findById(event.getAccountId()).ifPresent(account -> {
            if ("DEBIT".equalsIgnoreCase(event.getType())) {
                account.setBalance(account.getBalance().subtract(event.getAmount()));
            } else {
                account.setBalance(account.getBalance().add(event.getAmount()));
            }
            repo.save(account);
        });
    }
}
