package com.bytebank.service;

import com.bytebank.model.User;
<<<<<<< HEAD
import com.bytebank.model.OtpLog;
import com.bytebank.repository.UserRepository;
import com.bytebank.repository.OtpLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpLogRepository otpLogRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        // Generate Unique System ID following pattern: FIRSTNAME + 6 DIGITS + ROLE CODE
        String roleCode = user.getRole().toUpperCase();
        String firstNameClean = user.getFirstName().replaceAll("\\s+", "").toUpperCase();
        
        String systemId;
        SecureRandom random = new SecureRandom();
        do {
            int digits = 100000 + random.nextInt(900000);
            systemId = firstNameClean + digits + roleCode;
        } while (userRepository.existsById(systemId));
        
        user.setSystemId(systemId);
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setStatus("ACTIVE");
        user.setCreatedAt(LocalDateTime.now());
        
        return userRepository.save(user);
    }

    public Optional<User> findById(String systemId) {
        return userRepository.findById(systemId);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void createOtp(User user, String purpose, String otp) {
        // Store hashed OTP for safety
        String otpHash = passwordEncoder.encode(otp);
        OtpLog otpLog = OtpLog.builder()
                .user(user)
                .otpHash(otpHash)
                .purpose(purpose)
                .expiresAt(LocalDateTime.now().plusMinutes(5)) // 5 minutes validity
                .used(false)
                .build();
        otpLogRepository.save(otpLog);
    }

    public boolean verifyOtp(User user, String purpose, String rawOtp) {
        Optional<OtpLog> latestOtpLogOpt = otpLogRepository
                .findFirstByUserAndPurposeAndUsedFalseOrderByExpiresAtDesc(user, purpose);
        
        if (latestOtpLogOpt.isPresent()) {
            OtpLog otpLog = latestOtpLogOpt.get();
            if (otpLog.getExpiresAt().isAfter(LocalDateTime.now()) && passwordEncoder.matches(rawOtp, otpLog.getOtpHash())) {
                otpLog.setUsed(true);
                otpLogRepository.save(otpLog);
                return true;
            }
        }
        return false;
    }
=======
import com.bytebank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * Handles registration, System ID generation, password hashing, and lockout logic.
 * System ID pattern: FIRSTNAME + 6 DIGITS (SecureRandom) + ROLE CODE.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecureRandom secureRandom = new SecureRandom();

    public String generateSystemId(String firstName, User.Role role) {
        int sixDigits = 100000 + secureRandom.nextInt(900000);
        return firstName.toUpperCase() + sixDigits + role.name();
    }

    // TODO: register(), login attempt tracking, lockout after 5 failures (15 min),
    // password reset flow via OTP.
>>>>>>> 093ee2d (ByteBank V2 project stucture)
}
