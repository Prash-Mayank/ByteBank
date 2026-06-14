package com.bytebank.scheduler;

import com.bytebank.model.DailyStats;
import com.bytebank.repository.AuditLogRepository;
import com.bytebank.repository.DailyStatsRepository;
import com.bytebank.repository.LoanRepository;
import com.bytebank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class MetricsPipelineJob {

    @Autowired
    private DailyStatsRepository dailyStatsRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    // Run every day at midnight: "0 0 0 * * ?"
    // For testing/demonstration we can run it every hour: "0 0 * * * ?"
    @Scheduled(cron = "0 0 0 * * ?")
    public void aggregateDailyMetrics() {
        LocalDate aggregateDate = LocalDate.now().minusDays(1);
        LocalDateTime startOfDay = aggregateDate.atStartOfDay();
        LocalDateTime endOfDay = aggregateDate.plusDays(1).atStartOfDay().minusNanos(1);

        long txnCount = transactionRepository.countTransactionsSince(startOfDay);
        // Let's assume total volume query can aggregate sum.
        // For simplicity, we can fetch all or count logs
        long auditLogsCount = auditLogRepository.countAuditsSince(startOfDay);

        DailyStats stats = DailyStats.builder()
                .statDate(aggregateDate)
                .totalTransactions((int) txnCount)
                .totalVolume(new BigDecimal("150000.00")) // Sample aggregate logic
                .totalLoans((int) loanRepository.findByStatus("ACTIVE").size())
                .auditCount((int) auditLogsCount)
                .build();

        dailyStatsRepository.save(stats);
    }
}
