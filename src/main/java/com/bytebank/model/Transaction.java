package com.bytebank.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @Column(name = "txn_id", length = 50)
    private String txnId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_acc")
    private Account fromAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_acc")
    private Account toAccount;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 20)
    private String type; // TRANSFER, DEPOSIT, WITHDRAWAL, BILL_PAY, LOAN_DISBURSEMENT, EMI_DEBIT

    @Column(nullable = false, length = 20)
    private String status = "PENDING"; // PENDING, SUCCESS, FAILED

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    @Column(name = "gateway_ref", length = 100)
    private String gatewayRef;
=======
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * transactions table — all fund movements, AI-categorised after save.
 */
@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "txn_id")
    private Long txnId;

    @Column(name = "from_acc")
    private String fromAccount;

    @Column(name = "to_acc")
    private String toAccount;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TxnType type; // INTERNAL, NEFT, BILL_PAY, LOAN_DISBURSAL, EMI_DEBIT

    @Enumerated(EnumType.STRING)
    private TxnStatus status; // PENDING, SUCCESS, FAILED

    private LocalDateTime timestamp;

    @Column(name = "gateway_ref")
    private String gatewayRef;

    private String category; // AI-assigned: Food, Utilities, Transfer, Loan, etc.

    public enum TxnType { INTERNAL, NEFT, BILL_PAY, LOAN_DISBURSAL, EMI_DEBIT }
    public enum TxnStatus { PENDING, SUCCESS, FAILED }
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
