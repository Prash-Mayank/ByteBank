package com.bytebank.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @Column(name = "account_no", length = 20)
    private String accountNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "system_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 20)
    private String type; // SAVINGS, CURRENT, FIXED_DEPOSIT

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(nullable = false, length = 20)
    private String ifsc;

    @Column(nullable = false, length = 20)
    private String status = "ACTIVE"; // ACTIVE, FROZEN, CLOSED

    @Column(name = "opened_at", updatable = false)
    private LocalDateTime openedAt = LocalDateTime.now();

    @Column(name = "interest_rate", precision = 5, scale = 2)
    private BigDecimal interestRate = BigDecimal.ZERO;
=======
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * accounts table — bank accounts owned by a customer.
 */
@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @Column(name = "account_no", length = 12)
    private String accountNo;

    @Column(name = "system_id", nullable = false)
    private String systemId; // FK -> User

    @Enumerated(EnumType.STRING)
    private AccountType type; // SAVINGS, CURRENT, FD

    private BigDecimal balance;

    private String ifsc;

    @Enumerated(EnumType.STRING)
    private AccountStatus status; // ACTIVE, FROZEN, CLOSED

    @Column(name = "opened_at")
    private LocalDateTime openedAt;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    public enum AccountType { SAVINGS, CURRENT, FD }
    public enum AccountStatus { ACTIVE, FROZEN, CLOSED }
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
