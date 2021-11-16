# hotel Service

This service is responsible for the hotels which were created by users within the Microservice and it also contains its own database.


- Service Registry Name: `hotel-service`
- Run on Port `9002`
- MySQL Database
- Database Name: `hotelservicedb`


## Features
- Create your Hotel
- View and Updating of a hotel information
- View Hotel Information together with its user info
- Update Number of Rooms for a Hotel 
- Delete a Hotel

### User Service Routes Information

| Action                              | URL                                                                                  | 
| -------------                       |:---------------------------------------------------------------:                     | 
| Create a Hotel (POST)               | http://localhost:{API-GATEWAY-PORT}/hotel-service/api/hotels                         | 
| Get Hotel Info (GET)                | http://localhost:{API-GATEWAY-PORT}/hotel-service/api/hotels/{id}                    |   
| Update Hotel   (PUT)                | http://localhost:{API-GATEWAY-PORT}/hotel-service/api/hotels/{id}                    |  
| Delete Hotel (DELETE)               | http://localhost:{API-GATEWAY-PORT}/hotel-service/api/hotels/{id}                    | 
| Update Number of Hotel Rooms (PUT)  | http://localhost:{API-GATEWAY-PORT}/hotel-service/api/hotels/update-num-of-rooms/{id}|
| View Hotel + User info (Get)        | http://localhost:{API-GATEWAY-PORT}/hotel-service/api/hotels/user/{id}"              |
