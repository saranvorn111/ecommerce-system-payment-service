# e-commerce-system

A multi-module Java/Spring Boot e-commerce system, built as a learning project following a domain-driven design (DDD) style architecture with `order-service` as its first bounded context, followed by `payment-service`.

## Tech Stack

- Java 21
- Spring Boot 4.1.1
- Spring Data JPA + PostgreSQL
- Maven (multi-module)

## Project Structure

```
e-commerce-system/
├── common/                        # Shared modules used across services
│   ├── common-domain/             # Shared domain primitives (value objects, base entities, etc.)
│   ├── common-persistence/        # Shared persistence concerns (e.g. persistence exceptions)
│   ├── common-restapi/            # Shared REST API concerns (error handling, DTOs, etc.)
│   └── common-starter/            # Shared Spring Boot auto-configuration/starter
│
├── order-service/                 # Order bounded context
│   ├── order-service-domain/
│   │   ├── order-domain-core/         # Core domain model and business logic
│   │   └── order-application-service/ # Application/use-case layer
│   ├── order-service-persistence/     # JPA entities, repositories, mappers, and repository adapters
│   ├── order-service-restapi/         # REST API adapters (controllers)
│   ├── order-service-messaging/       # Messaging adapters (events, queues) — scaffolding only
│   └── order-service-main/            # Spring Boot application entry point, wiring all layers
│
└── payment-service/                # Payment bounded context
    └── payment-service-domain/
        ├── payment-domain-core/         # Core domain model and business logic
        └── payment-application-service/ # Application/use-case layer
    # persistence, REST API, messaging, and main modules not created yet
```

Each service follows a ports-and-adapters (hexagonal) layout: the domain core has no framework dependencies, and the persistence, REST API, and messaging modules act as adapters wired together in the `*-main` module.

## Building

```bash
./mvnw clean install
```

## Running

`order-service` connects to a PostgreSQL database on startup (configured in `order-service-main/src/main/resources/application.yaml`). Make sure a PostgreSQL instance is reachable at `jdbc:postgresql://localhost:8432/db_order` before running the service (Hibernate is configured with `ddl-auto: create-drop`, so the schema is created automatically).

```bash
./mvnw -pl order-service/order-service-main spring-boot:run
```

## API

### Create Order

```
POST /api/v1/orders
```

Request body:

```json
{
  "customerId": "UUID",
  "businessId": "UUID",
  "orderAddress": {
    "street": "string (1-20 chars)",
    "postalCode": "string (1-10 chars)",
    "city": "string (1-30 chars)"
  },
  "items": [
    {
      "productId": "UUID",
      "quantity": "integer",
      "price": "decimal",
      "subTotal": "decimal"
    }
  ],
  "price": "decimal"
}
```

All fields are required and validated with Bean Validation (`@NotNull`, `@Size`, etc.). A `201 Created` response returns:

```json
{
  "orderId": "UUID"
}
```

### Validation errors

Requests that fail validation return `400 Bad Request` via a shared `GlobalExceptionHandler` (in `common-restapi`), which each service's REST API module extends (e.g. `OrderGlobalExceptionHandler`):

```json
{
  "code": "Bad Request",
  "message": "Data validation failed",
  "detail": [
    { "field": "customerId", "code": "NotNull", "reason": "must not be null" }
  ]
}
```

## Dependency Visualization

Module dependencies can be visualized as a graph using the [depgraph-maven-plugin](https://github.com/ferstl/depgraph-maven-plugin), which relies on [Graphviz](https://graphviz.org/) to render the graph image.

### Install Graphviz

- Fedora: `sudo dnf install graphviz`
- Debian/Ubuntu: `sudo apt install graphviz`
- macOS (Homebrew): `brew install graphviz`

### Generate the dependency graph

```bash
mvn com.github.ferstl:depgraph-maven-plugin:aggregate -DcreateImage=true -DreduceEdges=false -Dscope=compile "-Dincludes=mptc-dtwg*:*"
```

This produces an aggregated dependency graph image (`dependency-graph.png`/`.dot`) for all modules matching `mptc-dtwg*:*` in the target directory.

## Status

This project is under active development as part of a Java/Spring learning exercise. The `order-service` module scaffolding is in place across all layers (domain, persistence, REST API, messaging). `payment-service` currently only has its domain layer in place.

What's implemented so far:

**order-service**
- `POST /api/v1/orders` endpoint with request validation and shared REST API error handling.
- `CreateOrderUseCase` is fully wired end to end: it looks up the customer and business (via `CustomerRepository`/`BusinessRepository`), delegates order validation and initiation to `OrderDomainService`, persists the order via `OrderRepository`, and returns the created order's ID.
- `OrderDomainService`/`OrderDomainServiceImpl` implements order validation and lifecycle transitions (initiate, pay, approve, cancel), including matching order items against the business's active products and confirming their name/price.
- JPA persistence for `Business`, `Customer`, and `Order` (entities, mappers, Spring Data repositories). `BusinessRepositoryAdapter`, `CustomerRepositoryAdapter`, and `OrderRepositoryAdapter#saveOrder` are all implemented; `BusinessPersistenceException` is raised when a business can't be found and mapped to a `400 Bad Request` by `OrderGlobalExceptionHandler`.
- `order-service-messaging` is scaffolded (module + dependency on `order-application-service`) with no messaging code yet.

**payment-service**
- `payment-domain-core`: `Payment` aggregate root (`orderId`, `customerId`, `price`, `paymentStatus`, `createdAt`) with `validatePayment()`, `initializePayment()`, and `updateStatus()`; `PaymentDomainService`/`PaymentDomainServiceImpl` orchestrates validation + initialization and status updates.
- `payment-application-service`: `CreatePaymentCommand`/`CreatePaymentResult` DTOs, `PaymentDomainMapper`, the `PaymentRepository` output port, and `CreatePaymentUseCase` wiring them together.
- New shared value objects `PaymentId` and `PaymentStatus` (`PENDING`, `COMPLETED`, `FAILED`, `CANCELLED`) added to `common-domain`.
- Persistence, REST API, messaging, and the runnable `*-main` module have not been built yet — `payment-service` doesn't run or expose an API yet.
