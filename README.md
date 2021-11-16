# Hotel Booking Microservice

This is the backend for the hotel booking application which utilizes the microservice architecture.
Users can create their profile, register their Hotel and users can book for rooms which are available in the hotel.

## Features
- Users Service with its own Database (More info [here](https://github.com/epaitoo/hotel-booking-microservice/tree/main/users-service))
- Hotel Service with ite own Database (More info [here](https://github.com/epaitoo/hotel-booking-microservice/tree/main/hotel-service))
- Booking Service with its own Database (More info [here](https://github.com/epaitoo/hotel-booking-microservice/tree/main/booking-service))
- Service Registration and Discovery Server using [Spring Cloud Netflix - Eureka](https://spring.io/projects/spring-cloud-netflix) 
- Api Gateway which acts a Load Balancer to route request coming from the client to a specific service. See more details [here](https://github.com/epaitoo/hotel-booking-microservice/tree/main/api-gateway)
- Cloud Config Server: A central [git repository](https://github.com/epaitoo/hotel-booking-microservice/tree/main/spring-cloud-config-server) on Github to store configurations and make them available for each service
- Circuit Breaker with [Resilience4j](https://resilience4j.readme.io/docs) for rate limiting, fallback methods, load reduction and retry request in case of temporal failure
- Distributed Tracing with [Zipkin](https://zipkin.io/) to trace request across microservices
- Containerization of Microservices with Docker and Docker Compose
- Container Orchestration with Kubernetes. See K8s yaml files [here](https://github.com/epaitoo/hotel-booking-microservice/tree/main/k8s)

### Image of Hotel Booking Microservice Architecture

![Hotel Booking Microservice Architecture](https://res.cloudinary.com/epaitoo/image/upload/v1637055984/others/microservice-img.jpg "Microservice Architecture Diagram")

## Services and Ports 

| Service Name        |     Port      | 
| -------------       |:-------------:| 
| User Service        | 9001          | 
| Hotel Service       | 9002          |   
| Booking Service     | 9003          | 
| Eureka Server       | 8761          |  
| Api Gateway         | 8765          |  
| Cloud Config Server | 8888          |  
| Zipkin Server       | 9411          |



## Getting Started

