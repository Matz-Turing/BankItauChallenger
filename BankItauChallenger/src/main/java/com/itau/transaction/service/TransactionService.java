package com.itau.transaction.service;

import com.itau.transaction.model.Statistics;
import com.itau.transaction.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TransactionService {
    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();
    
    @Value("${statistics.window.seconds:60}")
    private long statisticsWindowSeconds;

    public boolean addTransaction(Transaction transaction) {
        OffsetDateTime now = OffsetDateTime.now();
        
        if (transaction.getDataHora().isAfter(now)) {
            return false;
        }
        
        transactions.add(transaction);
        return true;
    }

    public void deleteAllTransactions() {
        transactions.clear();
    }

    public Statistics getStatistics() {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime windowStart = now.minusSeconds(statisticsWindowSeconds);

        List<Transaction> validTransactions = transactions.stream()
                .filter(t -> t.getDataHora().isAfter(windowStart) && !t.getDataHora().isAfter(now))
                .toList();

        if (validTransactions.isEmpty()) {
            return new Statistics();
        }

        DoubleSummaryStatistics stats = validTransactions.stream()
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();

        Statistics statistics = new Statistics();
        statistics.setCount(stats.getCount());
        statistics.setSum(stats.getSum());
        statistics.setAvg(stats.getAverage());
        statistics.setMin(stats.getMin());
        statistics.setMax(stats.getMax());

        return statistics;
    }

    @Scheduled(fixedRate = 60000)
    public void cleanOldTransactions() {
        OffsetDateTime cutoff = OffsetDateTime.now().minusSeconds(statisticsWindowSeconds);
        transactions.removeIf(t -> t.getDataHora().isBefore(cutoff));
    }
}
