# AI Powered Employee Management System

A secure Employee Management System developed using Spring Boot, Spring Security, JWT Authentication, OTP Email Verification, and AI-based Resume Screening.

---

# Features

- Employee CRUD Operations
- JWT Authentication & Authorization
- Spring Security
- OTP Email Verification
- AI Resume Screening
- REST APIs
- PostgreSQL Database Integration
- Send OTP Again Feature

---

# Technologies Used

- Java
- Spring Boot
- Spring Security
- JWT
- Hibernate / JPA
- PostgreSQL
- JavaMailSender
- Gradle
- Postman

---

# Project Structure

controller/
service/
repository/
model/
security/
dto/
exception/
ai/

---

# API Endpoints

## Authentication APIs

### Register

POST /auth/register

### Login

POST /auth/login

### Send OTP

POST /auth/send-otp

### Verify OTP

POST /auth/verify

### Resend OTP

POST /auth/send-otp

---

## Employee APIs

### Add Employee

POST /employee

### Get All Employees

GET /employee

### Get Employee By Id

GET /employee/{id}

### Update Employee

PUT /employee/{id}

### Delete Employee

DELETE /employee/{id}

---

# JWT Authentication

JWT token is required for secured APIs.

Example:

Authorization: Bearer TOKEN

---

# Database Configuration

Update application.properties:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/emsdata

spring.datasource.username=postgres

spring.datasource.password=yourpassword
```

# Run Project

```bash
./gradlew bootRun
```

# Future Enhancements

- Docker Integration
- Resume PDF Upload
- AI Resume Ranking
- Admin Dashboard

# Author

Kajal
