# Api Cloud Gateway

The API Gateway is responsible for request routing. It intercepts all requests from clients. It then routes the requests to the appropriate microservice.

Also installed as a dependancy is  `spring-boot-actuator` which is a spring boot library that provides services for monitoring  of the application


This service aslo registers to the Eureka Server using Eureka Client Service which is installed as dependancy.


## Feature
- Load Balancer for routing request to the appropriate microservice


## Getting Started

- Install Dependacies from pom.xml
- Run the service
