package com.bytebank.controller;

import com.bytebank.model.Loan;
import com.bytebank.model.User;
import com.bytebank.repository.LoanRepository;
import com.bytebank.service.LoanService;
import com.bytebank.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanService loanService;

    @Autowired
    private UserService userService;

    @GetMapping("/loans/pending")
    public ResponseEntity<?> getPendingLoans() {
        List<Loan> pendingLoans = loanRepository.findByStatus("APPLIED");
        return ResponseEntity.ok(pendingLoans);
    }

    @PostMapping("/loans/approve")
    public ResponseEntity<?> approveLoan(@RequestBody Map<String, String> request, HttpServletRequest servletRequest) {
        String loanId = request.get("loanId");
        String accountNo = request.get("accountNo");
        String ipAddress = servletRequest.getRemoteAddr();

        try {
            Loan approvedLoan = loanService.approveAndDisburseLoan(loanId, accountNo, ipAddress);
            return ResponseEntity.ok(approvedLoan);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/loans/reject")
    public ResponseEntity<?> rejectLoan(@RequestBody Map<String, String> request) {
        String loanId = request.get("loanId");
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan application not found."));

        if (!"APPLIED".equals(loan.getStatus())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Loan cannot be rejected in current status."));
        }

        loan.setStatus("REJECTED");
        loanRepository.save(loan);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/customer/lookup/{systemId}")
    public ResponseEntity<?> lookupCustomer(@PathVariable String systemId) {
        User user = userService.findById(systemId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found."));
        
        if ("ADM".equals(user.getRole())) {
            return ResponseEntity.status(403).body(Map.of("message", "Manager cannot view administrator details."));
        }
        return ResponseEntity.ok(user);
    }
}
