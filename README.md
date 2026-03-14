# Inventory Service

REST API for product inventory management built with **Spring Boot 3** and **Hexagonal Architecture**.

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 21 |
| Framework | Spring Boot 3.4.5 |
| Database | MongoDB (Spring Data) |
| Events | Spring Application Events (async) |
| Testing | Spock Framework 2.4 + Groovy 4 |
| Build | Gradle 8 |
| Container | Docker (multi-stage build) |

## Architecture

The project follows **Hexagonal Architecture** (Ports & Adapters), keeping the domain and application layers isolated from infrastructure concerns.

```
com.jsanchez.inventory
├── domain
│   ├── model          # Domain entities (Product)
│   └── event          # Domain events (ProductCreatedEvent)
├── application
│   ├── port
│   │   ├── in         # Use case interfaces (CreateProductUseCase, GetProductUseCase)
│   │   └── out        # Output port interfaces (SaveProductPort, LoadProductPort, EventPublisherPort)
│   └── service        # Use case implementations (CreateProductService, GetProductService)
└── infraestructure
    ├── adapter
    │   ├── in
    │   │   ├── rest   # HTTP controllers, DTOs, mappers
    │   │   └── event  # Async event listeners
    │   └── out
    │       ├── mongo  # MongoDB adapter (documents, repository)
    │       └── event  # Spring event publisher adapter
    └── config         # Bean wiring (UseCaseConfig)
```

## API Endpoints

### Create Product
```
POST /products
Content-Type: application/json

{
  "name": "Laptop",
  "quantity": 10
}
```

**Response `200 OK`**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Laptop",
  "quantity": 10
}
```

### Get Product
```
GET /products/{id}
```

**Response `200 OK`**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Laptop",
  "quantity": 10
}
```

## Running the Application

### Prerequisites
- Java 21
- MongoDB running on `localhost:27017`

### Local
```bash
./gradlew bootRun
```

### Docker
```bash
# Build image
docker build -t inventory-service .

# Run container (requires MongoDB)
docker run -p 8080:8080 inventory-service
```

## Running Tests

```bash
./gradlew test
```

Tests are written in **Spock Framework** (Groovy) and located under `src/test/groovy`.

## Changelog

### v0.1.0 — 2026-03-14

#### Added
- `CreateProductRequest` converted to Java record
- Spock Framework configured for unit testing
- Unit tests for `ProductController` covering create, get, and not-found scenarios
- Javadoc on `ProductController` (class, constructor, and all endpoints)

#### Fixed
- Missing `implements` declaration on `ProductMongoAdapter`

---

### v0.0.1 — 2025-05-06 · Initial release

#### Added
- Hexagonal architecture skeleton with domain, application, and infrastructure layers
- `Product` domain model
- `CreateProductUseCase` and `GetProductUseCase` port interfaces
- `CreateProductService` and `GetProductService` use case implementations
- `ProductController` REST adapter with `POST /products` and `GET /products/{id}` endpoints
- `ProductMongoAdapter` implementing `SaveProductPort` and `LoadProductPort` via Spring Data MongoDB
- `ProductRestMapper` for DTO ↔ domain mapping
- `ProductCreatedEvent` domain event published asynchronously on product creation
- `ProductEventListener` async listener (pending Kafka integration)
- `SpringEventPublisherAdapter` wiring Spring's `ApplicationEventPublisher` to the domain port
- `UseCaseConfig` for explicit use case bean wiring
- Multi-stage `Dockerfile` using Gradle + Eclipse Temurin JRE 21
- Spring Boot Actuator for health/metrics endpoints
