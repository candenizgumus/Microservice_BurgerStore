# Burger Store Project

## Overview
This project is an online burger store application built using a microservice architecture. Users can browse the menu, add items to their cart, place orders, and receive order confirmations. The backend is composed of multiple microservices developed with Java Spring Boot, utilizing various technologies for service communication, caching, and security.

## Features
- User authentication and authorization using JWT tokens
- Browse, add, and remove menu items
- Add items to the cart
- Place and confirm orders
- User account activation via email
- API Gateway for routing requests
- Config Client for centralized configuration management

## Technologies Used
### Microservices
- **Java Spring Boot**: Framework for building microservices.
- **Spring Cloud Gateway**: For routing and filtering requests to microservices.
- **Spring Cloud Config**: For centralized configuration management.
- **Spring Security**: For authentication and authorization.
- **Spring Data JPA**: For database interactions.
- **PostgreSQL**: For production database.

### Communication and Messaging
- **RabbitMQ**: For inter-service communication.

### Caching
- **Redis**: For caching frequently accessed data.

### Security
- **JWT (JSON Web Tokens)**: For securing API endpoints.

### Email
- **EmailSender**: For sending activation emails to users.

## Getting Started
### Prerequisites
- Java 11 or higher
- RabbitMQ server
- Redis server
- PostgreSQL server
- Node.js and npm (for frontend, if applicable)

### Microservices Setup
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/burger-store-backend.git
    cd burger-store-backend
    ```

2. Build and run each microservice:
    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

3. Ensure RabbitMQ, Redis, and PostgreSQL are running and properly configured.

### Config Server Setup
1. Clone the config repository:
    ```bash
    git clone https://github.com/yourusername/burger-store-config.git
    cd burger-store-config
    ```

2. Start the config server:
    ```bash
    ./mvnw spring-boot:run
    ```

### API Gateway Setup
1. Clone the API Gateway repository:
    ```bash
    git clone https://github.com/yourusername/burger-store-gateway.git
    cd burger-store-gateway
    ```

2. Start the API Gateway:
    ```bash
    ./mvnw spring-boot:run
    ```

## Usage
- Access the application via the API Gateway at `http://localhost:8080`
- Register a new account or log in with an existing account
- Browse the menu and add items to your cart
- Place an order and receive confirmation via email



