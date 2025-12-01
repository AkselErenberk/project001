Bookstore API
REST API for managing books, carts, and user authentication. Implements JWT-based authentication, CRUD for books, and a per-user shopping cart.

Tech Stack:

Java 17
Spring Boot (Web, Data JPA, Security)
JWT for authentication
PostgreSQL (via Docker Compose)
Maven for build
Lombok for boilerplate reduction

OpenAPI Contract:
The full OpenAPI specification is located at:
./source/bookstore/bookstore-contract-parent/bookstore-contracts/src/main/resources/contract.yaml

Prerequisites:

Java 17 installed (java -version)
Maven installed (mvn -v)
Docker & Docker Compose installed (docker -v, docker compose version)
IDE with Lombok plugin enabled and annotation processing turned on

Health check:
curl http://localhost:8080/api/hello-world

Build & Run (with maven and docker):
From ./source/bookstore execute:
mvn clean package -DskipTests

To run with docker, from the root execute:
docker build -t bookstore-api .
docker run --rm -p 8080:8080 bookstore-api

Run with Docker Compose (PostgreSQL + API):
docker compose up --build

Test with Postman:
Open Postman → Import → Upload file.
Set variables:

baseUrl: http://localhost:8080
jwtToken: (fill after login)

Use Auth → Login to get token, then test protected endpoints.


Or test with commandline with these curl Examples:

Auth:

Register:
curl -X POST http://localhost:8080/api/auth/register
-H "Content-Type: application/json"
-d '{"username":"john_doe","password":"secret123"}'

Login:
curl -X POST http://localhost:8080/api/auth/login
-H "Content-Type: application/json"
-d '{"username":"john_doe","password":"secret123"}'

Response:
{ "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." }


Books:

Get all books:
curl -X GET http://localhost:8080/api/books
-H "Authorization: Bearer <JWT_TOKEN>"

Add book:
curl -X POST http://localhost:8080/api/books
-H "Authorization: Bearer <JWT_TOKEN>"
-H "Content-Type: application/json"
-d '{"title":"Clean Code","author":"Robert C. Martin","price":39.99}'

Update book:
curl -X PUT http://localhost:8080/api/books/123
-H "Authorization: Bearer <JWT_TOKEN>"
-H "Content-Type: application/json"
-d '{"title":"Clean Code (Updated)","author":"Robert C. Martin","price":42.99}'

Delete book:
curl -X DELETE http://localhost:8080/api/books/123
-H "Authorization: Bearer <JWT_TOKEN>"


Cart:

View cart:
curl -X GET http://localhost:8080/api/cart
-H "Authorization: Bearer <JWT_TOKEN>"

Add item:
curl -X POST http://localhost:8080/api/cart
-H "Authorization: Bearer <JWT_TOKEN>"
-H "Content-Type: application/json"
-d '{"bookId":"123","quantity":2}'

Update quantity:
curl -X PUT http://localhost:8080/api/cart/123
-H "Authorization: Bearer <JWT_TOKEN>"
-H "Content-Type: application/json"
-d '{"quantity":3}'

Remove item:
curl -X DELETE http://localhost:8080/api/cart/123
-H "Authorization: Bearer <JWT_TOKEN>"