package com.bytebank.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/** ai_interactions table — chatbot conversation history per session. */
@Entity
@Table(name = "ai_interactions")
@Data
public class AiInteraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ai_id")
    private Long aiId;

    @Column(name = "system_id", nullable = false)
    private String systemId;

    @Column(name = "session_id")
    private String sessionId;

    @Column(columnDefinition = "TEXT")
    private String prompt;

    @Column(columnDefinition = "TEXT")
    private String response;

    private String model;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
