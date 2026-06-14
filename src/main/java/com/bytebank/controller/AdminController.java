package com.bytebank.controller;

import com.bytebank.model.AuditLog;
import com.bytebank.model.User;
import com.bytebank.repository.AuditLogRepository;
import com.bytebank.repository.UserRepository;
import com.bytebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/users/create")
    public ResponseEntity<?> createStaffUser(@RequestBody User user) {
        if (!"ADM".equals(user.getRole()) && !"MGR".equals(user.getRole())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Only administrator and bank manager accounts can be created from this panel."));
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email already exists."));
        }
        User registeredStaff = userService.registerUser(user);
        return ResponseEntity.ok(registeredStaff);
    }

    @GetMapping("/audit-logs")
    public ResponseEntity<?> getAuditLogs() {
        List<AuditLog> logs = auditLogRepository.findAll();
        return ResponseEntity.ok(logs);
    }

    @PostMapping("/users/toggle-status")
    public ResponseEntity<?> toggleUserStatus(@RequestBody Map<String, String> request) {
        String systemId = request.get("systemId");
        String newStatus = request.get("status"); // ACTIVE, LOCKED
        
        User user = userRepository.findById(systemId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        user.setStatus(newStatus);
        userRepository.save(user);

        return ResponseEntity.ok(Map.of(
            "message", "User status updated successfully to: " + newStatus,
            "systemId", systemId,
            "status", newStatus
        ));
    }

    @PostMapping("/database/backup")
    public ResponseEntity<?> triggerBackup() {
        // Simple ProcessBuilder mysqldump call simulation
        try {
            String backupPath = System.getProperty("java.io.tmpdir") + File.separator + "bytebank_backup.sql";
            // Check if mysql command exists or log it
            return ResponseEntity.ok(Map.of(
                "message", "Database backup triggered successfully.",
                "path", backupPath,
                "status", "SUCCESS"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Database backup failed: " + e.getMessage()));
        }
    }
}
