package com.bytebank.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fixed_deposits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FixedDeposit {
    @Id
    @Column(name = "fd_id", length = 50)
    private String fdId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_no", nullable = false)
    private Account account;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal principal;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal rate;

    @Column(nullable = false)
    private Integer tenure; // in months

    @Column(name = "maturity_date", nullable = false)
    private LocalDate maturityDate;

    @Column(nullable = false, length = 20)
    private String status = "ACTIVE"; // ACTIVE, MATURED, LIQUIDATED
}
