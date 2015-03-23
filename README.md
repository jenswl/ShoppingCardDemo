# ShoppingCartDemo
This is an example implementation of Spring Boot, Swagger and MongoDB. 

It provides a simple CRUD API to handle a shopping cart show case.

# How to start?
- Just provide a running MongoDB (e.g. via Docker or a local installation) on a standard host/port ("localhost:27017")
- Download the Swagger UI (https://github.com/swagger-api/swagger-ui) and run it locally - you do not need a server for that - just start index.html in your browser
- run the application via "mvn spring boot:run" or from Intellij by using the latest feature for Spring Boot run configurations 
- Enter the API url "http://localhost:8080/api-docs" into the Swagger UI.

# Possible next steps
- Combine with the very nice Spring Boot Admin dashboard (https://github.com/codecentric/spring-boot-admin)
- Add Spring HATEOAS (http://projects.spring.io/spring-hateoas/)
