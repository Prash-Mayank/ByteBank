package com.bytebank.repository;

import com.bytebank.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    @Query("SELECT COUNT(a) FROM AuditLog a WHERE a.timestamp >= :date")
    long countAuditsSince(@Param("date") LocalDateTime date);
}
