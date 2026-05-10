# Product Catalog with Custom LRU Cache

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Custom LRU Cache
- Git & GitHub

## Features

- Custom LRU Cache using HashMap + Doubly Linked List
- Product CRUD APIs
- Cache Aside Pattern
- Cache Synchronization
- Global Exception Handling
- Constructor Injection
- Logging

## APIs

- POST /products
- GET /products/{id}
- PUT /products/{id}
- DELETE /products/{id}

## Architecture

Client → Controller → Service → LRU Cache → Repository → MySQL
