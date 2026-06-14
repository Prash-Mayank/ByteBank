package com.bytebank.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "emi_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmiSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emi_id")
    private Long emiId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private Boolean paid = false;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "balance_after", precision = 15, scale = 2)
    private BigDecimal balanceAfter;
}
