package com.bytebank.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "beneficiaries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beneficiary {
=======
import lombok.Data;
import java.time.LocalDateTime;

/** beneficiaries table — saved transfer recipients pending manager approval. */
@Entity
@Table(name = "beneficiaries")
@Data
public class Beneficiary {

>>>>>>> 093ee2d (ByteBank V2 project stucture)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ben_id")
    private Long benId;

<<<<<<< HEAD
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
=======
    @Column(name = "owner_id", nullable = false)
    private String ownerId; // FK -> User.systemId

    @Column(name = "acc_no", nullable = false)
    private String accountNo;

    private String ifsc;

    @Column(name = "nick_name")
    private String nickName;

    @Enumerated(EnumType.STRING)
    private BeneficiaryStatus status; // PENDING, APPROVED, BLOCKED

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    public enum BeneficiaryStatus { PENDING, APPROVED, BLOCKED }
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
