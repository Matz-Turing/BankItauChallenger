# Transaction Statistics API

This project is a Spring Boot REST API that processes transactions and provides real-time statistics.

## Features

- Create transactions with amount and timestamp
- Delete all transactions
- Get statistics for transactions in the last 60 seconds
- Input validation
- API documentation with OpenAPI/Swagger
- Health check endpoints
- Configurable statistics window

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

## Building the Project

```bash
mvn clean install
```

## Running the Application

```bash
mvn spring-boot:run
```

The application will start on port 8080.

## API Endpoints

### Create Transaction
```
POST /transacao
Content-Type: application/json

{
    "valor": 123.45,
    "dataHora": "2024-02-02T01:23:45.678-03:00"
}
```

### Delete All Transactions
```
DELETE /transacao
```

### Get Statistics
```
GET /estatistica
```

## Documentation

- API Documentation (Swagger UI): http://localhost:8080/swagger-ui.html
- OpenAPI Specification: http://localhost:8080/api-docs

## Health Check

- Health endpoint: http://localhost:8080/actuator/health

## Docker Support

### Building the Docker Image
```bash
docker build -t transaction-statistics .
```

### Running with Docker
```bash
docker run -p 8080:8080 transaction-statistics
```

## Configuration

The following properties can be configured in `application.yml`:

- `statistics.window.seconds`: Time window for statistics calculation (default: 60)
