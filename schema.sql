-- CREATE DATABASE IF NOT EXISTS bytebank;
-- USE bytebank;

-- 1. Users Table
CREATE TABLE IF NOT EXISTS users (
    system_id VARCHAR(50) PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL, -- ADM, MGR, CUS
    email VARCHAR(150) UNIQUE NOT NULL,
    phone VARCHAR(20) NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE', -- ACTIVE, LOCKED, INACTIVE
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Accounts Table
CREATE TABLE IF NOT EXISTS accounts (
    account_no VARCHAR(20) PRIMARY KEY,
    system_id VARCHAR(50) NOT NULL,
    type VARCHAR(20) NOT NULL, -- SAVINGS, CURRENT, FIXED_DEPOSIT
    balance DECIMAL(15, 2) DEFAULT 0.00,
    ifsc VARCHAR(20) NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE', -- ACTIVE, FROZEN, CLOSED
    opened_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    interest_rate DECIMAL(5, 2) DEFAULT 0.00,
    FOREIGN KEY (system_id) REFERENCES users(system_id) ON DELETE CASCADE
);

-- 3. Transactions Table
CREATE TABLE IF NOT EXISTS transactions (
    txn_id VARCHAR(50) PRIMARY KEY,
    from_acc VARCHAR(20),
    to_acc VARCHAR(20),
    amount DECIMAL(15, 2) NOT NULL,
    type VARCHAR(20) NOT NULL, -- TRANSFER, DEPOSIT, WITHDRAWAL, BILL_PAY, LOAN_DISBURSEMENT, EMI_DEBIT
    status VARCHAR(20) DEFAULT 'PENDING', -- PENDING, SUCCESS, FAILED
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    gateway_ref VARCHAR(100),
    FOREIGN KEY (from_acc) REFERENCES accounts(account_no) ON DELETE SET NULL,
    FOREIGN KEY (to_acc) REFERENCES accounts(account_no) ON DELETE SET NULL
);

-- 4. Beneficiaries Table
CREATE TABLE IF NOT EXISTS beneficiaries (
    ben_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_id VARCHAR(50) NOT NULL,
    acc_no VARCHAR(20) NOT NULL,
    ifsc VARCHAR(20) NOT NULL,
    nick_name VARCHAR(100) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING', -- PENDING, APPROVED, BLOCKED
    approved_at TIMESTAMP NULL,
    FOREIGN KEY (owner_id) REFERENCES users(system_id) ON DELETE CASCADE
);

-- 5. Loans Table
CREATE TABLE IF NOT EXISTS loans (
    loan_id VARCHAR(50) PRIMARY KEY,
    system_id VARCHAR(50) NOT NULL,
    type VARCHAR(30) NOT NULL, -- PERSONAL, HOME, EDUCATION
    amount DECIMAL(15, 2) NOT NULL,
    tenure INT NOT NULL, -- in months
    rate DECIMAL(5, 2) NOT NULL,
    status VARCHAR(30) DEFAULT 'APPLIED', -- APPLIED, UNDER_REVIEW, APPROVED, REJECTED, ACTIVE, CLOSED
    emi DECIMAL(15, 2) NOT NULL,
    disbursed_at TIMESTAMP NULL,
    FOREIGN KEY (system_id) REFERENCES users(system_id) ON DELETE CASCADE
);

-- 6. EMI Schedule Table
CREATE TABLE IF NOT EXISTS emi_schedule (
    emi_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    loan_id VARCHAR(50) NOT NULL,
    due_date DATE NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    paid BOOLEAN DEFAULT FALSE,
    paid_at TIMESTAMP NULL,
    balance_after DECIMAL(15, 2),
    FOREIGN KEY (loan_id) REFERENCES loans(loan_id) ON DELETE CASCADE
);

-- 7. Payments Table
CREATE TABLE IF NOT EXISTS payments (
    pay_id VARCHAR(50) PRIMARY KEY,
    system_id VARCHAR(50) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    gateway VARCHAR(50) NOT NULL, -- RAZORPAY, STRIPE
    order_id VARCHAR(100),
    payment_id VARCHAR(100),
    status VARCHAR(20) DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (system_id) REFERENCES users(system_id) ON DELETE CASCADE
);

-- 8. Fixed Deposits Table
CREATE TABLE IF NOT EXISTS fixed_deposits (
    fd_id VARCHAR(50) PRIMARY KEY,
    account_no VARCHAR(20) NOT NULL,
    principal DECIMAL(15, 2) NOT NULL,
    rate DECIMAL(5, 2) NOT NULL,
    tenure INT NOT NULL, -- in months
    maturity_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE', -- ACTIVE, MATURED, LIQUIDATED
    FOREIGN KEY (account_no) REFERENCES accounts(account_no) ON DELETE CASCADE
);

-- 9. OTP Log Table
CREATE TABLE IF NOT EXISTS otp_log (
    otp_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    system_id VARCHAR(50) NOT NULL,
    otp_hash VARCHAR(255) NOT NULL,
    purpose VARCHAR(50) NOT NULL, -- LOGIN, PASSWORD_RESET, TRANSACTION_CONFIRM
    expires_at TIMESTAMP NOT NULL,
    used BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (system_id) REFERENCES users(system_id) ON DELETE CASCADE
);

-- 10. Audit Log Table
CREATE TABLE IF NOT EXISTS audit_log (
    log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(50),
    action VARCHAR(255) NOT NULL,
    entity VARCHAR(100),
    entity_id VARCHAR(100),
    ip_address VARCHAR(45),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(system_id) ON DELETE SET NULL
);

-- 11. Configuration Table
CREATE TABLE IF NOT EXISTS config (
    config_key VARCHAR(100) PRIMARY KEY,
    config_value VARCHAR(255) NOT NULL,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (updated_by) REFERENCES users(system_id) ON DELETE SET NULL
);

-- 12. Notifications Table
CREATE TABLE IF NOT EXISTS notifications (
    notif_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    system_id VARCHAR(50) NOT NULL,
    message TEXT NOT NULL,
    type VARCHAR(50) DEFAULT 'INFO',
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (system_id) REFERENCES users(system_id) ON DELETE CASCADE
);

-- 13. Daily Stats Reporting Table
CREATE TABLE IF NOT EXISTS daily_stats (
    stat_date DATE PRIMARY KEY,
    total_transactions INT DEFAULT 0,
    total_volume DECIMAL(15, 2) DEFAULT 0.00,
    total_loans INT DEFAULT 0,
    audit_count INT DEFAULT 0
);
