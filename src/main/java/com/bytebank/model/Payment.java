package com.bytebank.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @Column(name = "pay_id", length = 50)
    private String payId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id", nullable = false)
    private User user;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 50)
    private String gateway; // RAZORPAY, STRIPE

    @Column(name = "order_id", length = 100)
    private String orderId;

    @Column(name = "payment_id", length = 100)
    private String paymentId;

    @Column(nullable = false, length = 20)
    private String status = "PENDING"; // PENDING, SUCCESS, FAILED

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
=======
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/** payments table — Razorpay / Stripe gateway payment records. */
@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Long payId;

    @Column(name = "system_id", nullable = false)
    private String systemId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Gateway gateway; // RAZORPAY, STRIPE

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "payment_id")
    private String paymentId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // CREATED, PAID, FAILED

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public enum Gateway { RAZORPAY, STRIPE }
    public enum PaymentStatus { CREATED, PAID, FAILED }
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
