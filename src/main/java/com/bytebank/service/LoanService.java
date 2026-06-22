package com.bytebank.service;

<<<<<<< HEAD
import com.bytebank.model.Account;
import com.bytebank.model.EmiSchedule;
import com.bytebank.model.Loan;
import com.bytebank.model.User;
import com.bytebank.repository.AccountRepository;
import com.bytebank.repository.EmiScheduleRepository;
import com.bytebank.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private EmiScheduleRepository emiScheduleRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    public BigDecimal calculateEmi(BigDecimal principal, BigDecimal annualRate, int tenureMonths) {
        // EMI = [P x R x (1+R)^N]/[((1+R)^N)-1]
        // R = Monthly Interest Rate = Annual Rate / 12 / 100
        BigDecimal monthlyRate = annualRate.divide(new BigDecimal("1200"), 10, RoundingMode.HALF_UP);
        
        if (monthlyRate.compareTo(BigDecimal.ZERO) == 0) {
            return principal.divide(new BigDecimal(tenureMonths), 2, RoundingMode.HALF_UP);
        }

        BigDecimal onePlusRToN = monthlyRate.add(BigDecimal.ONE).pow(tenureMonths);
        BigDecimal numerator = principal.multiply(monthlyRate).multiply(onePlusRToN);
        BigDecimal denominator = onePlusRToN.subtract(BigDecimal.ONE);
        
        return numerator.divide(denominator, 2, RoundingMode.HALF_UP);
    }

    public Loan applyForLoan(User user, String type, BigDecimal amount, int tenureMonths, BigDecimal rate) {
        BigDecimal emi = calculateEmi(amount, rate, tenureMonths);
        Loan loan = Loan.builder()
                .loanId(UUID.randomUUID().toString())
                .user(user)
                .type(type)
                .amount(amount)
                .tenure(tenureMonths)
                .rate(rate)
                .status("APPLIED")
                .emi(emi)
                .build();
        return loanRepository.save(loan);
    }

    @Transactional
    public Loan approveAndDisburseLoan(String loanId, String savingsAccountNo, String ipAddress) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan application not found."));

        if (!"APPLIED".equals(loan.getStatus()) && !"UNDER_REVIEW".equals(loan.getStatus())) {
            throw new IllegalStateException("Loan has already been processed or is active.");
        }

        // Disburse funds to user's account
        transactionService.deposit(savingsAccountNo, loan.getAmount(), "LOAN_DISBURSEMENT", loan.getLoanId(), ipAddress);

        loan.setStatus("ACTIVE");
        loan.setDisbursedAt(LocalDateTime.now());
        loanRepository.save(loan);

        // Generate EMI Schedule
        List<EmiSchedule> schedules = new ArrayList<>();
        LocalDate baseDate = LocalDate.now().plusMonths(1);
        BigDecimal remainingBalance = loan.getAmount();

        for (int i = 1; i <= loan.getTenure(); i++) {
            // Recalculate interest portion and principal portion dynamically
            BigDecimal interestPortion = remainingBalance.multiply(loan.getRate().divide(new BigDecimal("1200"), 10, RoundingMode.HALF_UP));
            BigDecimal principalPortion = loan.getEmi().subtract(interestPortion);
            
            if (i == loan.getTenure()) {
                principalPortion = remainingBalance;
            }
            
            remainingBalance = remainingBalance.subtract(principalPortion);

            EmiSchedule schedule = EmiSchedule.builder()
                    .loan(loan)
                    .dueDate(baseDate.plusMonths(i - 1))
                    .amount(loan.getEmi())
                    .paid(false)
                    .balanceAfter(remainingBalance.max(BigDecimal.ZERO))
                    .build();
            schedules.add(schedule);
        }

        emiScheduleRepository.saveAll(schedules);
        return loan;
    }
=======
import com.bytebank.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Loan eligibility checks, EMI amortisation calculation, approval/rejection flow,
 * disbursement, and prepayment recalculation.
 */
@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    public BigDecimal calculateEmi(BigDecimal principal, BigDecimal annualRate, int tenureMonths) {
        // Standard reducing-balance EMI formula: P * r * (1+r)^n / ((1+r)^n - 1)
        BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(1200));
        // TODO: implement full formula with BigDecimal precision/rounding
        return BigDecimal.ZERO;
    }

    // TODO: checkEligibility(), approve(), reject(), disburse(), prepay()
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
