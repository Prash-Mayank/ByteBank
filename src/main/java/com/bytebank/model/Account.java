package com.bytebank.model;

import jakarta.persistence.*;
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
}
