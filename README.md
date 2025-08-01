# Social Media Backend API

A Spring Boot-based backend for a social media application.

## Features

- User registration and JWT authentication
- Secure password hashing with BCrypt
- Profile management (view own, view by username, update first/last name)
- Discussion posts (create, read, update, delete, list all, list by user, pagination)
- Comments on posts (add, list, delete, pagination)
- Consistent API responses and global error handling
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

## API Endpoints Overview

| Endpoint                                   | Method | Auth         | Description                              |
|--------------------------------------------|--------|--------------|------------------------------------------|
| /api/v1/auth/register                      | POST   | Public       | Register a new user                      |
| /api/v1/auth/login                         | POST   | Public       | Login and get JWT token                  |
| /api/v1/profile/me                         | GET    | Authenticated| Get current user's profile               |
| /api/v1/profile/{username}                 | GET    | Public       | Get any user's profile by username       |
| /api/v1/profile/me                         | PUT    | Authenticated| Update current user's profile            |
| /api/v1/posts                              | POST   | Authenticated| Create a new post                        |
| /api/v1/posts                              | GET    | Public       | List all posts (paginated)               |
| /api/v1/posts/{id}                         | GET    | Public       | Get post by ID                           |
| /api/v1/posts/{id}                         | PUT    | Authenticated| Update own post                          |
| /api/v1/posts/{id}                         | DELETE | Authenticated| Delete own post                          |
| /api/v1/posts/user/{username}              | GET    | Public       | List posts by user (paginated)           |
| /api/v1/comments/post/{postId}             | POST   | Authenticated| Add comment to post                      |
| /api/v1/comments/post/{postId}             | GET    | Public       | List comments for post (paginated)       |
| /api/v1/comments/{commentId}               | DELETE | Authenticated| Delete own comment                       |


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

## Usage Notes
- All endpoints (except registration/login and public GETs) require a valid JWT token in the `Authorization: Bearer <token>` header.
- Use Swagger UI at http://localhost:8080/swagger-ui.html for interactive API docs and testing.
- H2 console available at http://localhost:8080/h2-console for development DB access.
