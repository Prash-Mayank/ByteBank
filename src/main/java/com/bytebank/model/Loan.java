package com.bytebank.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {
    @Id
    @Column(name = "loan_id", length = 50)
    private String loanId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 30)
    private String type; // PERSONAL, HOME, EDUCATION

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private Integer tenure; // in months

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal rate;

    @Column(nullable = false, length = 30)
    private String status = "APPLIED"; // APPLIED, UNDER_REVIEW, APPROVED, REJECTED, ACTIVE, CLOSED

    @Column(nullable = false, precision = 15, scale = 2)
=======
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/** loans table — loan applications and active loans. */
@Entity
@Table(name = "loans")
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "system_id", nullable = false)
    private String systemId;

    @Enumerated(EnumType.STRING)
    private LoanType type; // PERSONAL, HOME, EDUCATION

    private BigDecimal amount;

    private Integer tenureMonths;

    private BigDecimal rate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status; // APPLIED, APPROVED, REJECTED, ACTIVE, CLOSED

>>>>>>> 093ee2d (ByteBank V2 project stucture)
    private BigDecimal emi;

    @Column(name = "disbursed_at")
    private LocalDateTime disbursedAt;
<<<<<<< HEAD
=======

    private String remarks;

    public enum LoanType { PERSONAL, HOME, EDUCATION }
    public enum LoanStatus { APPLIED, APPROVED, REJECTED, ACTIVE, CLOSED }
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
