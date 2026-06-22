package com.bytebank.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "otp_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpLog {
=======
import lombok.Data;
import java.time.LocalDateTime;

/** otp_log table — OTP verification records. */
@Entity
@Table(name = "otp_log")
@Data
public class OtpLog {

>>>>>>> 093ee2d (ByteBank V2 project stucture)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "otp_id")
    private Long otpId;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id", nullable = false)
    private User user;

    @Column(name = "otp_hash", nullable = false, length = 255)
    private String otpHash;

    @Column(nullable = false, length = 50)
    private String purpose; // LOGIN, PASSWORD_RESET, TRANSACTION_CONFIRM

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private Boolean used = false;
=======
    @Column(name = "system_id", nullable = false)
    private String systemId;

    @Column(name = "otp_hash", nullable = false)
    private String otpHash;

    @Enumerated(EnumType.STRING)
    private OtpPurpose purpose; // LOGIN, TRANSFER, PASSWORD_RESET

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    private boolean used;

    public enum OtpPurpose { LOGIN, TRANSFER, PASSWORD_RESET }
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
