package com.bytebank.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/** fraud_alerts table — AI-generated fraud alert records. */
@Entity
@Table(name = "fraud_alerts")
@Data
public class FraudAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Long alertId;

    @Column(name = "txn_id", nullable = false)
    private Long txnId;

    @Column(name = "risk_score")
    private Integer riskScore;

    @Column(name = "ai_summary", columnDefinition = "TEXT")
    private String aiSummary;

    private boolean reviewed;

    @Column(name = "reviewed_by")
    private String reviewedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
