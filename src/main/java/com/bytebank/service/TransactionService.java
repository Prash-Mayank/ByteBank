package com.bytebank.service;

import com.bytebank.model.Account;
import com.bytebank.model.AuditLog;
import com.bytebank.model.Transaction;
import com.bytebank.repository.AccountRepository;
import com.bytebank.repository.AuditLogRepository;
import com.bytebank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Transactional
    public Transaction processTransfer(String fromAccNo, String toAccNo, BigDecimal amount, String ipAddress) {
        Account fromAccount = accountRepository.findByAccountNoAndStatus(fromAccNo, "ACTIVE")
                .orElseThrow(() -> new IllegalArgumentException("Source account is inactive or not found."));

        Account toAccount = accountRepository.findByAccountNoAndStatus(toAccNo, "ACTIVE")
                .orElseThrow(() -> new IllegalArgumentException("Destination account is inactive or not found."));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds for transfer.");
        }

        // Limit Check: per transaction limit 1,00,000 INR
        if (amount.compareTo(new BigDecimal("100000.00")) > 0) {
            throw new IllegalArgumentException("Transaction exceeds per-transaction limit of 1,00,000 INR.");
        }

        // Update balances
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Record Transaction
        Transaction transaction = Transaction.builder()
                .txnId(UUID.randomUUID().toString())
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .amount(amount)
                .type("TRANSFER")
                .status("SUCCESS")
                .timestamp(LocalDateTime.now())
                .build();
        
        transactionRepository.save(transaction);

        // Audit Logging
        AuditLog log = AuditLog.builder()
                .user(fromAccount.getUser())
                .action("FUND_TRANSFER")
                .entity("Transaction")
                .entityId(transaction.getTxnId())
                .ipAddress(ipAddress)
                .timestamp(LocalDateTime.now())
                .build();
        
        auditLogRepository.save(log);

        return transaction;
    }

    @Transactional
    public Transaction deposit(String accountNo, BigDecimal amount, String type, String gatewayRef, String ipAddress) {
        Account account = accountRepository.findByAccountNoAndStatus(accountNo, "ACTIVE")
                .orElseThrow(() -> new IllegalArgumentException("Account not found or inactive."));

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .txnId(UUID.randomUUID().toString())
                .fromAccount(null) // Deposit comes from outside
                .toAccount(account)
                .amount(amount)
                .type(type) // DEPOSIT, BILL_PAY, etc.
                .status("SUCCESS")
                .timestamp(LocalDateTime.now())
                .gatewayRef(gatewayRef)
                .build();
        
        transactionRepository.save(transaction);

        AuditLog log = AuditLog.builder()
                .user(account.getUser())
                .action("DEPOSIT_" + type)
                .entity("Transaction")
                .entityId(transaction.getTxnId())
                .ipAddress(ipAddress)
                .timestamp(LocalDateTime.now())
                .build();
        
        auditLogRepository.save(log);

        return transaction;
    }
}
