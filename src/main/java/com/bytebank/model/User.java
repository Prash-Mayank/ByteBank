package com.bytebank.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "system_id", length = 50)
    private String systemId;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, length = 20)
    private String role; // ADM, MGR, CUS

    @Column(unique = true, nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 20)
    private String status = "ACTIVE"; // ACTIVE, LOCKED, INACTIVE

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
=======
import lombok.Data;
import java.time.LocalDateTime;

/**
 * users table — Admin, Bank Manager, and Customer accounts.
 * System ID pattern: FIRSTNAME + 6 DIGITS + ROLE CODE (e.g. PRIYA740182CUS).
 */
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "system_id", length = 32)
    private String systemId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // ADM, MGR, CUS

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private UserStatus status; // ACTIVE, LOCKED, DEACTIVATED

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public enum Role { ADM, MGR, CUS }
    public enum UserStatus { ACTIVE, LOCKED, DEACTIVATED }
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
