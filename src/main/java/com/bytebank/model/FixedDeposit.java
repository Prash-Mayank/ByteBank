package com.bytebank.model;

import jakarta.persistence.*;
<<<<<<< HEAD
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
=======
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

/** fixed_deposits table. */
@Entity
@Table(name = "fixed_deposits")
@Data
public class FixedDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fd_id")
    private Long fdId;

    @Column(name = "account_no", nullable = false)
    private String accountNo;

    private BigDecimal principal;

    private BigDecimal rate;

    private Integer tenureMonths;

    @Column(name = "maturity_date")
    private LocalDate maturityDate;

    @Enumerated(EnumType.STRING)
    private FdStatus status; // ACTIVE, MATURED, CLOSED

    public enum FdStatus { ACTIVE, MATURED, CLOSED }
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
