# E-Commerce Product Service Microservice

## Overview
The Product Service is a core microservice in an e-commerce backend system, responsible for managing product-related
operations. It acts as a bridge between the product catalog and other services like order management, inventory, and
recommendations. Built using Spring Boot, this microservice is designed to be scalable, maintainable, and efficient,
ensuring seamless integration with other components of the system..

## Features
- Product Management: Provides CRUD (Create, Read, Update, Delete) operations for managing product information. 
- Category Management: Handles hierarchical categorization of products to organize and simplify browsing. 
- Integration with External APIs: Integrates with external APIs, such as FakeStore API, to fetch and synchronize product data. 
- Search and Filtering: Supports advanced product search and filtering by categories, price range, and keywords. 
- Scalable Architecture: Built with RESTful principles to ensure easy scalability and maintainability. 
- WebFlux Support: Asynchronous request handling using Spring WebFlux for improved performance under heavy load.

## Key Technologies Used
Key Technologies Used

1. **Spring Boot 3.4.0**: Framework for building production-ready applications. 
2. **Lombok**: Reduces boilerplate code by generating getter, setter, and other utility methods.
3. **Maven**: Dependency management and build tool. 
4. **Java 17**: The latest LTS version, providing modern language features and performance improvements. 
5. **Spring WebFlux**: Reactive programming support for handling high-concurrency workloads. 
6. **REST APIs**: Implements RESTful endpoints for seamless integration with other services.

## Getting Started

### Prerequisites

- Java 17 
- Maven 
- Docker (optional, for containerized deployments)

### Steps to Run Locally

1. Clone the repository:

```
git clone https://github.com/yourusername/product-service.git
cd product-service
```


2. Build the project using Maven:
```
mvn clean install

```
3. Run the application:

```
mvn spring-boot:run
```
4. Access the service at http://localhost:8080
