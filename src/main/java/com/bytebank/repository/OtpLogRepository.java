package com.bytebank.repository;

import com.bytebank.model.OtpLog;
<<<<<<< HEAD
import com.bytebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OtpLogRepository extends JpaRepository<OtpLog, Long> {
    Optional<OtpLog> findFirstByUserAndPurposeAndUsedFalseOrderByExpiresAtDesc(User user, String purpose);
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpLogRepository extends JpaRepository<OtpLog, Long> {
    // TODO: add custom query methods as needed
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
