package com.bytebank.controller;

<<<<<<< HEAD
import com.bytebank.model.Account;
import com.bytebank.model.Loan;
import com.bytebank.model.Transaction;
import com.bytebank.model.User;
import com.bytebank.repository.TransactionRepository;
import com.bytebank.service.AccountService;
import com.bytebank.service.LoanService;
import com.bytebank.service.TransactionService;
import com.bytebank.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private TransactionRepository transactionRepository;

    private User getAuthenticatedUser() {
        String systemId = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findById(systemId)
                .orElseThrow(() -> new RuntimeException("Current user not found."));
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts() {
        User user = getAuthenticatedUser();
        List<Account> accounts = accountService.getAccountsByUser(user);
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/accounts/create")
    public ResponseEntity<?> openAccount(@RequestBody Map<String, String> request) {
        User user = getAuthenticatedUser();
        String type = request.getOrDefault("type", "SAVINGS");
        BigDecimal initialBalance = new BigDecimal(request.getOrDefault("initialBalance", "1000.00"));
        BigDecimal rate = "SAVINGS".equalsIgnoreCase(type) ? new BigDecimal("3.5") : BigDecimal.ZERO;
        
        Account account = accountService.createAccount(user, type, initialBalance, rate);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferFunds(@RequestBody Map<String, String> request, HttpServletRequest servletRequest) {
        String fromAccNo = request.get("fromAccountNo");
        String toAccNo = request.get("toAccountNo");
        BigDecimal amount = new BigDecimal(request.get("amount"));
        String ipAddress = servletRequest.getRemoteAddr();

        try {
            Transaction transaction = transactionService.processTransfer(fromAccNo, toAccNo, amount, ipAddress);
            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/transactions/{accountNo}")
    public ResponseEntity<?> getTransactions(@PathVariable String accountNo) {
        Account account = accountService.getAccountByNo(accountNo)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        
        // Assert user ownership
        if (!account.getUser().getSystemId().equals(getAuthenticatedUser().getSystemId())) {
            return ResponseEntity.status(403).body(Map.of("message", "Access denied."));
        }

        List<Transaction> txns = transactionRepository.findByFromAccountOrToAccountOrderByTimestampDesc(account, account);
        return ResponseEntity.ok(txns);
    }

    @PostMapping("/loans/apply")
    public ResponseEntity<?> applyForLoan(@RequestBody Map<String, String> request) {
        User user = getAuthenticatedUser();
        String type = request.get("type"); // PERSONAL, HOME, EDUCATION
        BigDecimal amount = new BigDecimal(request.get("amount"));
        int tenure = Integer.parseInt(request.get("tenure"));
        BigDecimal rate = new BigDecimal(request.getOrDefault("rate", "8.5"));

        Loan loan = loanService.applyForLoan(user, type, amount, tenure, rate);
        return ResponseEntity.ok(loan);
    }
=======
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** Dashboard, transfers, loan application, bill pay, statements, spending insights. */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    // TODO: GET /dashboard, POST /transfer, POST /loans/apply, GET /statement,
    // GET /spending-insights, POST /fixed-deposit
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
