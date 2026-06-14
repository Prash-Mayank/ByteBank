package com.bytebank.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "daily_stats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyStats {
    @Id
    @Column(name = "stat_date")
    private LocalDate statDate;

    @Column(name = "total_transactions")
    private Integer totalTransactions = 0;

    @Column(name = "total_volume", precision = 15, scale = 2)
    private BigDecimal totalVolume = BigDecimal.ZERO;

    @Column(name = "total_loans")
    private Integer totalLoans = 0;

    @Column(name = "audit_count")
    private Integer auditCount = 0;
}
