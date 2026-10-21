# Movie API Example — Fall 2026

A Spring Boot REST API for managing **movies and snacks**. The project uses Java, Spring Data JPA, and MySQL, and exposes JSON endpoints for creating, viewing, searching, updating, and deleting records.

## Features

- Create, list, retrieve, search, and delete movies.
- Search movies by genre or by part of the title.
- Record whether a movie is available in **IMAX** and/or **3D**.
- Create, list, retrieve, update, partially update, and delete snacks.
- Persist entities in MySQL using Spring Data JPA.
- Explore and test endpoints with the generated Swagger UI.

## Tech stack

- Java 25
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA / Hibernate
- MySQL Connector/J
- Maven
- JUnit and Spring Boot Test
- Springdoc OpenAPI / Swagger UI

## Project structure

```text
src/
├── main/
│   ├── java/com/movieapi/
│   │   ├── MovieApp.java
│   │   ├── movie/
│   │   │   ├── Movie.java
│   │   │   ├── MovieController.java
│   │   │   ├── MovieRepository.java
│   │   │   └── MovieService.java
│   │   └── snack/
│   │       ├── Snack.java
│   │       ├── SnackController.java
│   │       ├── SnackRepository.java
│   │       └── SnackService.java
│   └── resources/application.properties
└── test/java/com/movieapi/
    ├── movie/MovieServiceTest.java
    └── snack/SnackServiceTest.java
```

The application entry point is `com.movieapi.MovieApp`.

## Getting started

### Prerequisites

- JDK 25
- Maven
- MySQL Server running locally (default connection: `localhost:3306`)

### 1. Create the database

Create a MySQL schema named `fall2026` before starting the application, for example in MySQL Workbench:

```sql
CREATE DATABASE fall2026;
```

The configured database username is `root`. If you use a different MySQL username, update `spring.datasource.username` in `src/main/resources/application.properties`.

### 2. Set the database password

The password is deliberately not stored in the repository. The application reads it from the `DB_PASSWORD` environment variable.

**Git Bash:**

```bash
export DB_PASSWORD='your_mysql_password'
```

Replace the example value with your local MySQL password. Do not commit real passwords or paste them into public issues or documentation. Keep the variable set in the same terminal session where you run the app.

### 3. Run the application

From the project root:

```bash
mvn spring-boot:run
```

The API runs at `http://localhost:8080`.

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

## API endpoints

All endpoints are relative to `http://localhost:8080`.

### Movies

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/movies` | Save a movie |
| `GET` | `/movies/getAllMovies` | Return all movies |
| `GET` | `/movies/{movieId}` | Get one movie by ID |
| `DELETE` | `/movies/{id}` | Delete a movie by ID |
| `GET` | `/movies/searchByGenre/{genre}` | Find movies by genre |
| `GET` | `/movies/searchByTitle?title={text}` | Search titles containing the supplied text (case-insensitive) |

Example request body for `POST /movies`:

```json
{
  "title": "Avatar",
  "genre": "Science Fiction",
  "releaseDate": "2026-10-21",
  "availableInIMAX": true,
  "availableIn3D": true
}
```

The `availableInIMAX` and `availableIn3D` properties are independent booleans. For example, a movie can be available in IMAX without being available in 3D. If omitted, Java boolean fields default to `false`.

The movie-create endpoint currently responds with the message `Movie saved successfully`. The `GET /movies/getAllMovies` endpoint returns the stored movie objects, including both availability fields.

Example title search:

```http
GET /movies/searchByTitle?title=Avatar
```

### Snacks

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/snacks` | List all snacks; optionally filter with `?maxPrice=5` |
| `GET` | `/snacks/{id}` | Get a snack by ID |
| `POST` | `/snacks` | Create a snack |
| `PUT` | `/snacks/{id}` | Replace/update a snack |
| `PATCH` | `/snacks/{id}` | Partially update a snack |
| `DELETE` | `/snacks/{id}` | Delete a snack |

Example request body for `POST /snacks`:

```json
{
  "name": "Buttered Popcorn",
  "type": "Salty",
  "price": 8.50,
  "shareable": true
}
```

## Run tests

From the project root:

```bash
mvn test
```

The tests cover the movie availability properties and snack service behavior. Run the command locally to see the current test results.

## Configuration notes

The database connection is configured in `src/main/resources/application.properties`. It currently points to the local MySQL schema `fall2026`, uses the `root` username, and reads the password from `DB_PASSWORD`. Hibernate is configured with `spring.jpa.hibernate.ddl-auto=update`, so it updates the schema based on entity changes when the application starts.

For deployment or shared environments, use environment-specific configuration and a least-privilege database user rather than a root account. Never commit credentials.

## Troubleshooting

| Problem | Things to check |
| --- | --- |
| MySQL access denied | Confirm `DB_PASSWORD` and the configured username. |
| Connection refused / communications link failure | Confirm MySQL is running and listening on port `3306`. |
| Unknown database `fall2026` | Create the schema or correct the datasource URL. |
| Port `8080` already in use | Stop the other process or configure a different server port. |
| Swagger UI does not load | Confirm the application started successfully, then open `/swagger-ui/index.html`. |

## Repository

[View this project on GitHub](https://github.com/feraszen/Movie_Api_Example_Fall_2026)
