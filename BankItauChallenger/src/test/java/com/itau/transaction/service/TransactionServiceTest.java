package com.itau.transaction.service;

import com.itau.transaction.model.Statistics;
import com.itau.transaction.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionServiceTest {

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService();
        ReflectionTestUtils.setField(transactionService, "statisticsWindowSeconds", 60L);
    }

    @Test
    void whenValidTransaction_thenAccepted() {
        Transaction transaction = new Transaction();
        transaction.setValor(100.0);
        transaction.setDataHora(OffsetDateTime.now().minusSeconds(30));

        assertTrue(transactionService.addTransaction(transaction));
    }

    @Test
    void whenFutureTransaction_thenRejected() {
        Transaction transaction = new Transaction();
        transaction.setValor(100.0);
        transaction.setDataHora(OffsetDateTime.now().plusSeconds(1));

        assertFalse(transactionService.addTransaction(transaction));
    }

    @Test
    void whenGetStatistics_withValidTransactions_thenCorrectValues() {
        // Add some transactions
        Transaction t1 = new Transaction();
        t1.setValor(100.0);
        t1.setDataHora(OffsetDateTime.now().minusSeconds(30));
        transactionService.addTransaction(t1);

        Transaction t2 = new Transaction();
        t2.setValor(200.0);
        t2.setDataHora(OffsetDateTime.now().minusSeconds(45));
        transactionService.addTransaction(t2);

        Statistics stats = transactionService.getStatistics();
        
        assertEquals(2, stats.getCount());
        assertEquals(300.0, stats.getSum());
        assertEquals(150.0, stats.getAvg());
        assertEquals(100.0, stats.getMin());
        assertEquals(200.0, stats.getMax());
    }

    @Test
    void whenDeleteTransactions_thenStatisticsAreZero() {
        // Add a transaction
        Transaction transaction = new Transaction();
        transaction.setValor(100.0);
        transaction.setDataHora(OffsetDateTime.now().minusSeconds(30));
        transactionService.addTransaction(transaction);

        // Delete all transactions
        transactionService.deleteAllTransactions();

        Statistics stats = transactionService.getStatistics();
        assertEquals(0, stats.getCount());
        assertEquals(0.0, stats.getSum());
        assertEquals(0.0, stats.getAvg());
        assertEquals(0.0, stats.getMin());
        assertEquals(0.0, stats.getMax());
    }

    @Test
    void whenOldTransactions_thenNotIncludedInStatistics() {
        // Add an old transaction
        Transaction oldTransaction = new Transaction();
        oldTransaction.setValor(100.0);
        oldTransaction.setDataHora(OffsetDateTime.now().minusSeconds(61));
        transactionService.addTransaction(oldTransaction);

        Statistics stats = transactionService.getStatistics();
        assertEquals(0, stats.getCount());
    }
}
