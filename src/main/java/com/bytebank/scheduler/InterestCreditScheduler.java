package com.bytebank.scheduler;

import com.bytebank.model.Account;
import com.bytebank.model.Transaction;
import com.bytebank.repository.AccountRepository;
import com.bytebank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class InterestCreditScheduler {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // Run on the first day of every month at 2:00 AM
    @Scheduled(cron = "0 0 2 1 * ?")
    @Transactional
    public void creditMonthlyInterest() {
        List<Account> accounts = accountRepository.findAll();
        
        for (Account account : accounts) {
            if ("SAVINGS".equals(account.getType()) && "ACTIVE".equals(account.getStatus())) {
                BigDecimal rate = account.getInterestRate();
                if (rate.compareTo(BigDecimal.ZERO) > 0) {
                    // Monthly interest = Balance * (Rate / 12 / 100)
                    BigDecimal monthlyRate = rate.divide(new BigDecimal("1200"), 10, RoundingMode.HALF_UP);
                    BigDecimal interestAmount = account.getBalance().multiply(monthlyRate).setScale(2, RoundingMode.HALF_UP);

                    if (interestAmount.compareTo(BigDecimal.ZERO) > 0) {
                        account.setBalance(account.getBalance().add(interestAmount));
                        accountRepository.save(account);

                        // Log Transaction
                        Transaction txn = Transaction.builder()
                                .txnId(UUID.randomUUID().toString())
                                .fromAccount(null)
                                .toAccount(account)
                                .amount(interestAmount)
                                .type("INTEREST_CREDIT")
                                .status("SUCCESS")
                                .timestamp(LocalDateTime.now())
                                .build();
                        transactionRepository.save(txn);
                    }
                }
            }
        }
    }
}
