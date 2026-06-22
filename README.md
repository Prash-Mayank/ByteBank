<<<<<<< HEAD
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



=======
# ByteBank — Digital Banking Platform

Academic full-stack digital banking platform built with Spring Boot, MySQL, and free-tier AI
features (Google Gemini + local Ollama). Fully containerised with Docker Compose for
one-command deployment.

> Project lead: Mayank Prashar · 18-week build plan · 2025

## Tech Stack

| Layer | Technology |
|---|---|
| Frontend | HTML5, JSP, Tailwind CSS, Chart.js, vanilla JS |
| Backend | Spring Boot 3.x, Spring MVC |
| Security | Spring Security, JWT, BCrypt, OTP (Gmail SMTP) |
| Database | MySQL 8.x, JPA / Hibernate |
| Payments | Razorpay / Stripe (test mode) |
| AI | Gemini API (free tier) + Ollama (local) |
| Containers | Docker, Docker Compose, Nginx |

## Project Structure

```
src/main/java/com/bytebank/
├── config/        SecurityConfig, JwtConfig, MailConfig, AiConfig
├── controller/     AuthController, AdminController, ManagerController, CustomerController, AiController
├── model/          JPA entities: User, Account, Transaction, Loan, Payment, FraudAlert, ...
├── repository/     Spring Data JPA repositories
├── service/        UserService, AccountService, TransactionService, LoanService, AiService, ...
├── security/       JwtAuthFilter, JwtUtil, UserDetailsServiceImpl
├── payment/        RazorpayService, StripeService, WebhookController
├── ai/             GeminiClient, OllamaClient, FraudDetectionService, CategoryService
├── scheduler/      EmiDebitScheduler, InterestCreditScheduler, TransferScheduler
├── dto/            Request/response payloads
└── exception/      GlobalExceptionHandler and custom exceptions

src/main/resources/
├── application.properties
├── templates/WEB-INF/templates/   JSP views: login, dashboard, transfer, loan, admin, chat-widget
└── static/js/                     main.js, transfer.js, loanCalc.js, charts.js, chat.js

docker-compose.yml            Full stack: Spring Boot, MySQL, Ollama, Nginx
docker-compose.override.yml   Local dev overrides (hot reload, debug port)
Dockerfile                    Multi-stage Maven build -> slim JRE image
nginx/nginx.conf              Reverse proxy config
.env.example                  Environment variable template
```

## Getting Started

### Prerequisites
- Docker Desktop (Windows/macOS) or Docker Engine + Docker Compose (Linux)
- Git
- A free Google Gemini API key from https://aistudio.google.com
- A free Razorpay test account (no credit card required)

### Run with Docker

```bash
git clone https://github.com/your-username/bytebank.git
cd bytebank
cp .env.example .env
# fill in real values in .env
docker compose up --build -d
```

Wait ~60 seconds for the health check to pass, then open http://localhost:8080
(or http://localhost via the Nginx proxy on port 80).

The Ollama container automatically pulls the `llama3.2` model on first start.

### Useful Docker commands

```bash
docker compose ps                       # view running containers
docker compose logs -f bytebank-app      # stream application logs
docker compose down                      # stop all services
docker compose down -v                   # stop and remove volumes (wipes DB)
docker compose up --build bytebank-app   # rebuild after code changes
```

### Run locally without Docker (optional)

```bash
# requires a local MySQL 8.x instance and the env vars exported in your shell
mvn clean package -DskipTests
java -jar target/bytebank.jar
```

## Status

This repository currently contains the **scaffolded project structure** — package layout,
JPA entities matching the database schema, repository interfaces, service/controller stubs,
security skeleton, AI client stubs, and the full Docker setup. Business logic (marked `TODO`
throughout) is implemented module-by-module following the 18-week phased plan below.

## 18-Week Implementation Timeline

| Phase | Weeks | Key Deliverables |
|---|---|---|
| Setup & Architecture | 1–2 | Scaffolding, DB schema, Git init, Tailwind config, Docker skeleton |
| Auth Module | 3–4 | Registration, login, JWT, Spring Security, BCrypt, OTP, lockout |
| Account Management | 5–6 | Account CRUD, dashboard, mini statement, interest scheduler, PDF export |
| Fund Transfer | 7–8 | Internal/external transfers, OTP confirmation, beneficiaries, scheduling |
| Payment Gateway | 9–10 | Razorpay integration, webhook verification, bill payment simulation |
| Loan Module | 11–12 | Apply/approve flow, EMI calculator, auto-debit scheduler, amortisation PDF |
| AI Features | 13–14 | Gemini chatbot, Ollama categorisation, fraud alerts, spending insights |
| Dashboards & Reports | 15–16 | Admin/Manager/Customer dashboards, Chart.js, PDF/CSV reports |
| Docker Finalisation | 17 | Multi-stage Dockerfile, Compose health checks, Nginx, .env docs |
| Testing & Hardening | 18 | Unit/integration tests, security audit, docs, README, submission |

## License

Academic project — for educational/portfolio purposes.
>>>>>>> 093ee2d (ByteBank V2 project stucture)
