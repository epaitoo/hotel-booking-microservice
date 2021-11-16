# Users Service

This service is responsible for the Users within the Microservice and it contains its own database.


- Service Registry Name: `users-service`
- Run on Port `9001`
- MySQL Database
- Database Name: `userservicedb`
- Eureka Client for Service Registration
- DockerFile available 

## Features
- User Registration
- View and Updating of a user
- Delete a user

### User Service Routes Information

| Action                | URL                                                              | 
| -------------         |:---------------------------------------------------------------: | 
| Create a User (POST)  | http://localhost:{API-GATEWAY-PORT}/users-service/api/users      | 
| Get User Info (GET)   | http://localhost:{API-GATEWAY-PORT}/users-service/api/users/{id} |   
| Update User   (PUT)   | http://localhost:{API-GATEWAY-PORT}/users-service/api/users/{id} |  
| Delete User (DELETE)  | http://localhost:{API-GATEWAY-PORT}/users-service/api/users/{id} | 
