package com.example.transaction.service;

import com.example.common.dto.TransactionEvent;
import com.example.transaction.entity.TransactionEntity;
import com.example.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repo;
    private final KafkaTemplate<String, TransactionEvent> kafka;

    @Transactional
    public TransactionEntity createAndPublish(UUID accountId, BigDecimal amount, String type){
        UUID txId = UUID.randomUUID();
        TransactionEntity entity = TransactionEntity.builder()
                .id(txId)
                .accountId(accountId)
                .amount(amount)
                .type(type)
                .timestamp(Instant.now())
                .status("CREATED")
                .build();
        repo.save(entity);

        TransactionEvent event = new TransactionEvent(txId, accountId, amount, type, Instant.now());
        kafka.send("transactions", accountId.toString(), event);
        entity.setStatus("PUBLISHED");
        repo.save(entity);
        return entity;
    }
}
