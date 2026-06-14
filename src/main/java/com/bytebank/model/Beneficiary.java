package com.bytebank.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "beneficiaries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ben_id")
    private Long benId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "acc_no", nullable = false, length = 20)
    private String accNo;

    @Column(nullable = false, length = 20)
    private String ifsc;

    @Column(name = "nick_name", nullable = false, length = 100)
    private String nickName;

    @Column(nullable = false, length = 20)
    private String status = "PENDING"; // PENDING, APPROVED, BLOCKED

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;
}
