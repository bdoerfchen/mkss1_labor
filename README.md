## Order System

### Setup
1. Start RabbitMQ Server
   1. Use Docker Compose to start the server using the provided `docker-compose.yml` file
2. Start the Order System
   1. Make sure all required projects are recognized by IntelliJ as submodules (`mkss-shared`, `order-system`, `events`)
   2. In IntelliJ, start `OrderSystemApplicationKt`

### API Reference
In order to learn more about the API, you can:
1. Open the [Swagger UI](http://localhost:8080/swagger-ui/index.html)
2. Open the [OpenAPI spec file](http://localhost:8080/v3/api-docs) served by the server

You may also use the [Postman Collection](doc/postman/collections/OrderSystem-API.postman_collection.json) which is 
provided with this project to query the API.