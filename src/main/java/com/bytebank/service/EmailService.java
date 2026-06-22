package com.bytebank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/** Sends OTP and transaction alert emails via Gmail SMTP (JavaMailSender). */
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    // TODO: sendOtp(), sendTransactionAlert(), sendFraudAlert(), sendPasswordReset()
}
