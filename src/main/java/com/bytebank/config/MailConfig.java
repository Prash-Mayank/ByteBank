package com.bytebank.config;

import org.springframework.context.annotation.Configuration;

/**
 * Mail configuration placeholder.
 * Actual SMTP connection properties (host, port, username, password) are configured
 * via application.properties / .env (MAIL_USERNAME, MAIL_PASSWORD) and consumed by
 * Spring Boot's auto-configured JavaMailSender bean.
 *
 * Add custom MimeMessage helpers here if OTP/alert email templates need shared logic.
 */
@Configuration
public class MailConfig {
}
