package com.bytebank.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
=======
import lombok.Data;
import java.time.LocalDateTime;

/** notifications table — in-app alerts and notices per user. */
@Entity
@Table(name = "notifications")
@Data
public class Notification {

>>>>>>> 093ee2d (ByteBank V2 project stucture)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notif_id")
    private Long notifId;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id", nullable = false)
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(length = 50)
    private String type = "INFO"; // INFO, ALERT, SUCCESS, DANGER

    @Column(name = "is_read")
    private Boolean isRead = false;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
=======
    @Column(name = "system_id", nullable = false)
    private String systemId;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotifType type; // INFO, WARNING, FRAUD_ALERT, ANNOUNCEMENT

    private boolean read;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public enum NotifType { INFO, WARNING, FRAUD_ALERT, ANNOUNCEMENT }
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
