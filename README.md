# Social Media Backend API

A Spring Boot-based backend for a social media application.

## Features

- User registration and authentication
- Profile management
- Discussion posts
- JWT authentication
- Password hashing with BCrypt
- API documentation with Swagger/OpenAPI

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- H2 Database (for development)
- PostgreSQL (for production)

### Installation

1. Clone the repository
2. Build the project:
   ```
   mvn clean install
   ```
3. Run the application:
   ```
   mvn spring-boot:run
   ```

## Development

### Database
- H2 Console: http://localhost:8080/h2-console
  - JDBC URL: jdbc:h2:mem:socialmediadb
  - Username: sa
  - Password: password

### API Documentation
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI docs: http://localhost:8080/v3/api-docs

## Project Structure

```
src/main/java/com/example/socialmedia/
├── config/           # Configuration classes
├── controller/       # REST Controllers
├── dto/              # Data Transfer Objects
├── exception/        # Custom exceptions
├── model/            # JPA Entities
├── repository/       # JPA Repositories
├── security/         # Security configurations
└── service/          # Business logic
```
