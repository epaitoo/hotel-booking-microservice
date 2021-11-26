# Hotel Booking Microservice

This is the backend for the hotel booking application which utilizes the microservice architecture.
Users can create their profile, register their Hotel and users can book for rooms which are available in the hotel.


## Features
- Users Service with its own Database (More info [here](https://github.com/epaitoo/hotel-booking-microservice/tree/main/users-service#readme))
- Hotel Service with ite own Database (More info [here](https://github.com/epaitoo/hotel-booking-microservice/tree/main/hotel-service#readme))
- Booking Service with its own Database (More info [here](https://github.com/epaitoo/hotel-booking-microservice/tree/main/booking-service#readme))
- Service Registration and Discovery Server using [Spring Cloud Netflix - Eureka](https://spring.io/projects/spring-cloud-netflix). More Details [here](https://github.com/epaitoo/hotel-booking-microservice/tree/main/hotel-booking-service-registry#readme)
- Api Gateway which acts a Load Balancer to route request coming from the client to a specific service. See more details [here](https://github.com/epaitoo/hotel-booking-microservice/tree/main/api-gateway#readme). 
- Cloud Config Server: A central [git repository](https://github.com/epaitoo/hotel-booking-microservice/tree/main/spring-cloud-config-server#readme) on Github to store configurations and make them available for each service
- Circuit Breaker with [Resilience4j](https://resilience4j.readme.io/docs) for rate limiting, fallback methods, load reduction and retry request in case of temporal failure
- Distributed Tracing with [Zipkin](https://zipkin.io/) to trace request across microservices
- Containerization of Microservices with Docker and Docker Compose
- Container Orchestration with Kubernetes. See K8s yaml files [here](https://github.com/epaitoo/hotel-booking-microservice/tree/main/k8s)
- [Amazon Elastic Kubernetes Service (Amazon EKS)](https://aws.amazon.com/eks/) is used to deploy, manage and scale Kubernetes in the cloud

### Image of Hotel Booking Microservice Architecture

![Hotel Booking Microservice Architecture](https://res.cloudinary.com/epaitoo/image/upload/v1637055984/others/microservice-img.jpg "Microservice Architecture Diagram")

## API Documentation & Live Preview
Try out the API Endpoint: 

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/7868806-4b756402-d947-4272-84c3-539335e5eeb3?action=collection%2Ffork&collection-url=entityId%3D7868806-4b756402-d947-4272-84c3-539335e5eeb3%26entityType%3Dcollection%26workspaceId%3D88fe0bf2-c56f-4b60-8217-5f4164b5461d)

API Documentation [here](https://documenter.getpostman.com/view/7868806/UVCCeP6Y) 



## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

### Application Setup with Docker & Docker Compose

- Click on the 'Clone or download' button and select 'Download Zip.'
- At the root of the project is the `docker-compose.yaml` file which contains all the images for each service and its environment variables
- Run the `docker-compose up` command in the terminal to get the project up and running.

### Application Setup without Docker
- Click on the 'Clone or download' button and select 'Download Zip.'
- Make sure you have mysql installed your local machine. see how to install [here](https://dev.mysql.com/doc/refman/8.0/en/installing.html)
- Open your fav IDE and navigate to <em>hotel-booking-service-registry Folder</em> and install dependancies from the pom.xml
(NB: The Service Registry must be run first so that it can register other services which will be run later)
- Navigate to the other services for instance, spring-cloud-config folder and install its dependacies and run. Do the same for all the remaining services.




