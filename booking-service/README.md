# Booking Service

This service is responsible for booking of hotels within the Microservice and it also contains its own database.
It also interacts with both User Service and Hotel Service.


- Service Registry Name: `booking-service`
- Run on Port `9003`
- MySQL Database
- Database Name: `bookingservicedb`


## Features
- Book A Hotel
- View List of Hotels Booked by a User
- View Booking Info


### User Service Routes Information

| Action                              | URL                                                                                     | 
| -------------                       |:-----------------------------------------------------------------------------------:    | 
| Book a Hotel (POST)                 | http://localhost:{API-GATEWAY-PORT}/booking-service/api/bookings/add-booking/{hotel-id} | 
| Get Hotel Booked by a User (GET)    | http://localhost:{API-GATEWAY-PORT}/booking-service/api/bookings/user-bookings/{user-id}|   
| View Booking (GET)                  | http://localhost:{API-GATEWAY-PORT}/booking-service/api/bookings/{id}                   |  

