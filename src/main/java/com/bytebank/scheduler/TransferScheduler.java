package com.bytebank.scheduler;

import com.bytebank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** Picks up and executes future-dated transfers scheduled by customers. */
@Component
@RequiredArgsConstructor
public class TransferScheduler {

    private final TransactionRepository transactionRepository;

    @Scheduled(cron = "0 */15 * * * *") // every 15 minutes
    public void processScheduledTransfers() {
        // TODO: find PENDING future-dated transfers due now, execute via TransactionService
    }
}
