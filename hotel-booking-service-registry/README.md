# Service Registry & Discovery Server

This service holds the information about all services.

All the Microservices will register into this Server and this service knows all services running on each port and IP address.

We use Eureka which is developed and used by [Netflix](https://github.com/spring-cloud/spring-cloud-netflix) for its Service Discovery Server and Client. [Eureka](https://github.com/spring-cloud/spring-cloud-netflix) is open source.

Eureka Server is used here to discover all microservices which are registered with Eureka Client Service. 

Each service must have Eureka Client installed and enabled for it to be discovered by this Eureka Server

## Feature
- Discovers microservices within the applicaion

### NB
This service should be run first before running other services. This is done to help the other services Eureka Client locate this server and successfully register to it.

## Getting Started

- Install Dependacies from pom.xml
- Run the server on http://localhost:8761
- Keep this server running for other services to register to it
