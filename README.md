# Web Store API

A containerized **Spring Boot e-commerce backend** built as a practice and demonstration project.

This application exposes REST APIs for authentication, products, carts, orders, and payments, and is designed with **clean architecture, security best practices, and real-world production patterns** in mind.

> ⚠️ This is a **proof-of-concept project**. It is not intended to run a real store or handle real user data.

---

## Features

- **Java 17**
- **Spring Boot 3.5**
- **MySQL database**
- **RESTful API design**
- **JWT-based authentication**
    - Access tokens
    - Refresh tokens stored as HTTP-only cookies
- **Role-based authorization**
- **Flyway database migrations**
- **Stripe integration**
    - Checkout sessions
    - Webhook handling
- **Dockerized for local development**
- **Prod-ready design patterns**
    - Single-Responsibility Principle:
        - Controllers → HTTP / networking
        - Services → business logic
        - Repositories → persistence
    - Consistent error handling using a shared `ErrorDto`
- **API documentation & testing**
    - **OpenAPI**/**SwaggerUI** support for documentation
    - **Postman** test flows (yet to add)
