package com.bytebank.scheduler;

import com.bytebank.model.Account;
import com.bytebank.model.EmiSchedule;
import com.bytebank.model.Transaction;
import com.bytebank.repository.AccountRepository;
import com.bytebank.repository.EmiScheduleRepository;
import com.bytebank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class EmiDebitScheduler {

    @Autowired
    private EmiScheduleRepository emiScheduleRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // Run daily at 1:00 AM to process EMI debits
    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional
    public void processEmiDebits() {
        LocalDate today = LocalDate.now();
        List<EmiSchedule> pendingEmis = emiScheduleRepository.findByPaidFalseAndDueDateLessThanEqual(today);

        for (EmiSchedule emi : pendingEmis) {
            // Locate user's primary savings account
            List<Account> userAccounts = accountRepository.findByUser(emi.getLoan().getUser());
            Account savingsAccount = userAccounts.stream()
                    .filter(acc -> "SAVINGS".equals(acc.getType()) && "ACTIVE".equals(acc.getStatus()))
                    .findFirst()
                    .orElse(null);

            if (savingsAccount != null && savingsAccount.getBalance().compareTo(emi.getAmount()) >= 0) {
                // Deduct EMI
                savingsAccount.setBalance(savingsAccount.getBalance().subtract(emi.getAmount()));
                accountRepository.save(savingsAccount);

                emi.setPaid(true);
                emi.setPaidAt(LocalDateTime.now());
                emi.setBalanceAfter(savingsAccount.getBalance());
                emiScheduleRepository.save(emi);

                // Log Transaction
                Transaction txn = Transaction.builder()
                        .txnId(UUID.randomUUID().toString())
                        .fromAccount(savingsAccount)
                        .toAccount(null)
                        .amount(emi.getAmount())
                        .type("EMI_DEBIT")
                        .status("SUCCESS")
                        .timestamp(LocalDateTime.now())
                        .gatewayRef(emi.getLoan().getLoanId())
                        .build();
                transactionRepository.save(txn);
            } else {
                // Insufficient funds, transaction fails or logged as pending penalty
                Transaction txn = Transaction.builder()
                        .txnId(UUID.randomUUID().toString())
                        .fromAccount(savingsAccount)
                        .toAccount(null)
                        .amount(emi.getAmount())
                        .type("EMI_DEBIT")
                        .status("FAILED")
                        .timestamp(LocalDateTime.now())
                        .gatewayRef(emi.getLoan().getLoanId())
                        .build();
                transactionRepository.save(txn);
            }
        }
    }
}
