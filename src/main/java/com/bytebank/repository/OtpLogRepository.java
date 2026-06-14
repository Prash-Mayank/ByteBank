package com.bytebank.repository;

import com.bytebank.model.OtpLog;
import com.bytebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OtpLogRepository extends JpaRepository<OtpLog, Long> {
    Optional<OtpLog> findFirstByUserAndPurposeAndUsedFalseOrderByExpiresAtDesc(User user, String purpose);
}
