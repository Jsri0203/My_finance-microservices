# Finance Microservices Demo

A demonstration Java microservices architecture for showcasing full-stack backend skills in the finance domain. Built with Spring Boot, Spring Cloud components, Kafka, PostgreSQL, Redis, Docker, and GitHub Actions.

## Services
- **api-gateway** — Spring Cloud Gateway + route + Swagger UI
- **auth-service** — OAuth2-like JWT auth (simple example)
- **account-service** — Account CRUD, balances, caching
- **transaction-service** — Create and process transactions, integrated with Kafka

## Tech stack
- Java 17, Spring Boot (Spring Web, Data JPA, Security, Kafka)
- PostgreSQL, Redis
- Kafka (local via docker-compose)
- Docker & Docker Compose
- Flyway for DB migrations
- MapStruct + Lombok for DTO mapping
- OpenAPI/Swagger
- GitHub Actions CI (mvn test, build, docker build)

## Quick start (local)
1. Install Docker & Docker Compose
2. From repo root:
   ```bash
   docker-compose up --build
