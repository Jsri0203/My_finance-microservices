package com.example.transaction.service;

import com.example.transaction.entity.TransactionEntity;
import com.example.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionServiceTest {
    @Test
    void createAndPublish_savesTransaction() {
        TransactionRepository repo = Mockito.mock(TransactionRepository.class);
        KafkaTemplate<String, Object> kafka = Mockito.mock(KafkaTemplate.class);

        TransactionService service = new TransactionService(repo, kafka);
        UUID accId = UUID.randomUUID();
        Mockito.when(repo.save(any(TransactionEntity.class))).thenAnswer(i -> i.getArgument(0));

        TransactionEntity res = service.createAndPublish(accId, BigDecimal.valueOf(10), "DEBIT");
        assertNotNull(res.getId());
    }
}
