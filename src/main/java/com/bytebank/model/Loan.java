package com.bytebank.model;

import jakarta.persistence.*;
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
    private BigDecimal emi;

    @Column(name = "disbursed_at")
    private LocalDateTime disbursedAt;
}
