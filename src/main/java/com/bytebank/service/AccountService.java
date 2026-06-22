package com.bytebank.service;

<<<<<<< HEAD
import com.bytebank.model.Account;
import com.bytebank.model.User;
import com.bytebank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(User user, String type, BigDecimal initialBalance, BigDecimal interestRate) {
        // Generate Unique 12-digit Account Number
        String accountNo;
        SecureRandom random = new SecureRandom();
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 12; i++) {
                sb.append(random.nextInt(10));
            }
            accountNo = sb.toString();
        } while (accountRepository.existsById(accountNo));

        Account account = Account.builder()
                .accountNo(accountNo)
                .user(user)
                .type(type)
                .balance(initialBalance)
                .ifsc("BBAN0009981") // Standard IFSC code for all ByteBank branches
                .status("ACTIVE")
                .openedAt(LocalDateTime.now())
                .interestRate(interestRate)
                .build();

        return accountRepository.save(account);
    }

    public List<Account> getAccountsByUser(User user) {
        return accountRepository.findByUser(user);
    }

    public Optional<Account> getAccountByNo(String accountNo) {
        return accountRepository.findById(accountNo);
    }

    public Optional<Account> getActiveAccountByNo(String accountNo) {
        return accountRepository.findByAccountNoAndStatus(accountNo, "ACTIVE");
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }
=======
import com.bytebank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Account CRUD, 12-digit account number generation, mini statements,
 * status management (Active/Frozen/Closed), and interest crediting.
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    // TODO: openAccount(), generateAccountNumber(), getMiniStatement(), freeze(), close()
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
