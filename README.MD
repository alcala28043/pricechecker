# Price Checker Service

A Spring Boot service that retrieves the applicable price for a product and brand at a specific date, following the Hexagonal Architecture (Ports & Adapters) pattern.

If multiple prices are valid on the given date, the one with the highest priority is returned.

---

## 🛠 Tech Stack

| Technology | Description |
|------------|-------------|
| Java       | 21 (`record`,`var`)         |
| Spring Boot| 3.5.3       |
| Build      | Maven        |
| REST API   | Spring Web MVC |
| Persistence| Spring Data JPA + Flyway |
| Database   | H2 (in-memory) |
| Validation | JSR-380 (`@Valid`) |
| Testing    | JUnit 5 + Mockito + MockMvc |
| Documentation | SpringDoc OpenAPI UI |

---

## 🎯 Endpoints
The service provides auto-generated OpenAPI documentation using SpringDoc OpenAPI 

Swagger UI: `http://localhost:8080/swagger-ui/index.html`

---

## 📊 Actuator Endpoints

Health check and metrics available at:

- [GET] `/actuator/health`

- [GET] `/actuator/metrics`

- [GET] `/actuator/info`

All actuator endpoints are exposed under:

- [GET] `/actuator`

---

## 💾 H2 In-Memory Database
You can access the H2 console to view database contents during development at 

`http://localhost:8080/h2-console`


| url | user | password |
|-----|------|----------|
| `jdbc:h2:mem:pricechecker` | `sa` | `<empty>` |

---

## 🏃 How to run

### Clone the project:

```
git clone https://github.com/yourusername/pricechecker.git 
cd pricechecker
```

### Run with Maven

```
./mvnw spring-boot:run
```

## 📋 Unit & Integration Tests

### Run All tests:
```
./mvnw test
```

### Run only Unit tests:
```
./mvnw test -Dtest=PriceServiceTest
```

### Run only Integration tests:
```
./mvnw test -Dtest=PriceRestControllerTest
```

---

