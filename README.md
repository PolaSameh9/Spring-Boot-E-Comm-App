# SpringEcom

A backend e-commerce backend built with **Spring Boot** featuring secure authentication, OAuth2 login, product management, image uploads, order processing, filtering, pagination, and PostgreSQL persistence.

---

# Features

- JWT Authentication
- OAuth2 Login (Google & GitHub)
- Role-based authorization
- Product CRUD
- Category CRUD
- Order management
- Product image upload & retrieval
- Product search
- Dynamic filtering using Spring Specifications
- Pagination & sorting
- PostgreSQL database
- DTO mapping
- Docker support

---

# Tech Stack

### Backend

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring OAuth2 Client
- Hibernate
- Maven

### Database

- PostgreSQL

### Utilities

- JWT
- Lombok
- Jackson
- MapStruct

### DevOps

- Docker
- Docker Compose

---

# Project Structure

```
src
 ├── config
 ├── controller
 ├── mapper
 ├── model
     ├── dto
 ├── repo
 ├── service
 ├── specification
 └── resources
```

---

# Getting Started

## Prerequisites

- Java JDK
- Maven
- PostgreSQL

(Optional)

- Docker
- Docker Compose

---

## Clone the repository

```bash
git clone https://github.com/polasameh9/SpringEcom.git

cd SpringEcom
```

---

## Configure the application

Update:

```
src/main/resources/application.properties
```

Configure:

- PostgreSQL username/password
- JWT secret
- Google OAuth credentials
- GitHub OAuth credentials
---

## Run the application

### Maven

```bash
./mvnw spring-boot:run
```

Windows

```powershell
.\mvnw.cmd spring-boot:run
```

---

### Build

```bash
./mvnw clean package

java -jar target/*.jar
```

---

### Docker

```bash
docker compose up --build
```

---

# REST API

Base URL

```
http://localhost:8090/api
```

## Authentication

| Method | Endpoint |
|---------|----------|
| POST | /register |
| POST | /login |

---

## Products

| Method | Endpoint |
|---------|----------|
| GET | /products |
| GET | /product/{id} |
| POST | /product |
| PUT | /product/{id} |
| DELETE | /product/{id} |
| GET | /products/search |
| GET | /products/filtering&pagination&sorting |
| GET | /product/{id}/image |

---

## Categories

| Method | Endpoint |
|---------|----------|
| GET | /categories |
| GET | /category/{id} |
| POST | /category |
| PUT | /category/{id} |
| DELETE | /category/{id} |

---

## Orders

| Method | Endpoint |
|---------|----------|
| POST | /orders |
| GET | /orders |
| PUT | /order/{orderId} |

---

# Authentication

Protected endpoints require:

```
Authorization: Bearer <JWT_TOKEN>
```

---

# Example Login

```http
POST /api/login
```

```json
{
  "email": "user@example.com",
  "password": "password"
}
```

Response

```json
{
  "token": "eyJhbGc..."
}
```

---

# What I Learned

This project helped me gain practical experience with:

- Spring Security
- JWT Authentication
- OAuth2 Login
- REST API Design
- Spring Data JPA
- Hibernate Relationships
- DTO Mapping
- Product Image Uploads
- Pagination & Sorting
- Dynamic Filtering with Specifications
- PostgreSQL Integration
- Docker

---

# Future Improvements

- Unit & Integration Tests
- Refresh Tokens
- Role Management UI
- Swagger/OpenAPI Documentation
- Redis Caching
- Global Exception Handling
- Payment Gateway Integration

---

# License

This project is open for learning purposes.
