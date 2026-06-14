# ByteBank : Digital Banking Platform

## Overview

ByteBank is a full-stack digital banking web application built for academic demonstration purposes. It simulates core banking operations including account management, fund transfers, loan management, authentication, payment gateway integration, and role-based access control.

---



## Features

### Authentication & Security

- User Registration and Login

- JWT Authentication

- Spring Security Integration

- BCrypt Password Encryption

- OTP Email Verification

- Role-Based Access Control

- Account Lockout Protection

- Session Management

- Audit Logging



### Account Management

- Savings Accounts

- Current Accounts

- Fixed Deposit Accounts

- Account Dashboard

- Transaction History

- PDF Statement Generation



### Fund Transfer System

- Internal Transfers

- Inter-bank Transfer Simulation

- Beneficiary Management

- OTP Verification

- Transaction Receipts



### Loan Management


- Personal Loans

- Home Loans

- Education Loans

- EMI Calculator

- Loan Tracking



### Payment Integration

- Razorpay Integration

- Stripe Integration

- Webhook Verification

- Bill Payment Simulation



### Dashboard Modules

- Admin Dashboard

- Manager Dashboard

- Customer Dashboard

---



## Technology Stack

### Frontend

- HTML5

- CSS3

- Tailwind CSS

- JavaScript (ES6+)

- JSP



### Backend

- Java

- Spring Boot

- Spring MVC

- Spring Security



### Database

- MySQL

- Hibernate / JPA


### Tools

- Maven

- Git

- GitHub

- Postman

- VS Code



---

## Project Structure

```text

bytebank/

├── src/

│   ├── main/

│   │   ├── java/com/bytebank/

│   │   │   ├── config/

│   │   │   ├── controller/

│   │   │   ├── model/

│   │   │   ├── repository/

│   │   │   ├── service/

│   │   │   ├── security/

│   │   │   ├── payment/

│   │   │   └── scheduler/

│   │   └── resources/

│   │       ├── templates/

│   │       ├── static/

│   │       └── application.properties

├── pom.xml

└── README.md

```

---



## User Roles

### Admin

- Manage Users

- View Reports

- Configure System


### Bank Manager

- Approve Loans

- Manage Customers

- Monitor Accounts


### Customer

- Transfer Money

- Apply for Loans

- Manage Accounts

---

## Installation

### Clone Repository

```bash

git clone https://github.com/yourusername/bytebank.git

cd bytebank

```
### Create Database

```sql

CREATE DATABASE bytebank;

```

### Configure Application

Update `application.properties`

```properties

spring.datasource.url=jdbc:mysql://localhost:3306/bytebank
spring.datasource.username=root
spring.datasource.password=yourpassword

```

### Build Project

```bash
mvn clean install
```

### Run Project

```bash
mvn spring-boot:run
```

Application URL:

```text

http://localhost:8080

```
---

## Environment Variables

```properties

JWT_SECRET=

MAIL_USERNAME=

MAIL_PASSWORD=

RAZORPAY_KEY=

RAZORPAY_SECRET=

```
---

## Security Features

- JWT Authentication

- BCrypt Password Hashing

- OTP Verification

- Role-Based Authorization

- HttpOnly Cookies

- Rate Limiting

- Input Validation
---


## Future Improvements

- Mobile Banking App

- Docker Deployment

- Microservices

- AI Fraud Detection



---



## License
Academic and Educational Purpose Licence[licance]

---

## Author

**Mayank Prashar**

Project Lead | Full Stack Developer

2025



