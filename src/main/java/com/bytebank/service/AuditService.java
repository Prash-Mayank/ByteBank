package com.bytebank.service;

import com.bytebank.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Records login, logout, and critical actions with timestamp and IP. */
@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    // TODO: log(String userId, String action, String entity, String entityId, String ip)
}
