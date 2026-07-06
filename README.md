# SpringEcom

A simple Spring Boot e-commerce backend example providing products, categories, orders, and user authentication (JWT + OAuth2).

## Features

- **REST API** for products, categories, orders and users
- **JWT authentication** and OAuth2 (Google / GitHub) login
- **Product image upload** (multipart)
- **Filtering, pagination, and sorting** for products
- Uses **PostgreSQL** (configurable) and Spring Data JPA

## Tech Stack

- Java (configured in `pom.xml`)
- Spring Boot (Web, Security, Data JPA, OAuth2)
- PostgreSQL
- Maven wrapper included (`mvnw`, `mvnw.cmd`)

## Getting Started

Prerequisites

- Java (see `pom.xml` for configured version) — JDK installation required
- PostgreSQL running and accessible (or update `application.properties` to use another DB)
- Docker & Docker Compose (optional)

Quick run (development)

Windows (PowerShell):

```powershell
.\mvnw.cmd spring-boot:run
```

Unix/macOS:

```bash
./mvnw spring-boot:run
```

Build jar

```bash
./mvnw package
java -jar target/spring-ecom.jar
```

Docker (if you prefer containers)

```bash
docker-compose up -d --build
```

Configuration

Modify [src/main/resources/application.properties](src/main/resources/application.properties#L1-L36) to set your database credentials, OAuth client ids/secrets, and JWT secret. Do NOT commit secrets to the repository.

## API Endpoints (summary)

All endpoints are rooted under `/api`.

- Users
  - `POST /api/register` — register new user (JSON `User` object)
  - `POST /api/login` — login (JSON `AuthRequest`) — returns JWT token

- Products
  - `GET /api/products` — list all products
  - `GET /api/product/{id}` — get product by id
  - `GET /api/product/{productId}/image` — get raw image bytes
  - `POST /api/product` — add product (multipart: `product` JSON + `imageFile`)
  - `PUT /api/product/{id}` — update product (multipart)
  - `DELETE /api/product/{id}` — delete product
  - `GET /api/products/search?keyword=...` — search products
  - `GET /api/products/filtering&pagination&sorting?...` — filtering/pagination/sorting

- Categories
  - `POST /api/categories` — create category
  - `GET /api/categories` — list categories
  - `GET /api/categories/{id}` — get category
  - `PUT /api/categories/{id}` — update
  - `DELETE /api/categories/{id}` — delete

- Orders
  - `POST /api/orders` — place an order (JSON `OrderRequest`)
  - `GET /api/orders` — list orders
  - `PUT /api/order/{orderId}/status` — update order status

Authentication

- Most write endpoints require authentication via JWT. Add header: `Authorization: Bearer <token>` after login.

Example: login + use token

```bash
curl -X POST http://localhost:8090/api/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"secret"}'
# response contains token; use in Authorization header for protected endpoints
```

Product upload example (curl)

```bash
curl -X POST http://localhost:8090/api/product \
  -H "Authorization: Bearer $TOKEN" \
  -F "product={\"productName\":\"My product\",\"productPrice\":19.99};type=application/json" \
  -F "imageFile=@/path/to/image.jpg"
```

## Tests

Run unit tests with:

```bash
./mvnw test
```

## Contributing

Contributions are welcome. Open an issue or submit a PR.

## License

This repository does not include a license file. Add one if you plan to publish it publicly.
