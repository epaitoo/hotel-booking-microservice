package com.epaitoo.booking.service;

import com.epaitoo.booking.VO.Hotel;
import com.epaitoo.booking.entity.Booking;
import com.epaitoo.booking.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }


    public boolean saveBooking(Long hotelId, Booking booking) {
        log.info("Inside saveBooking of BookingService");

        //Get the Hotel
        Hotel hotel = restTemplate.getForObject("http://hotel-service/api/hotels/" + hotelId, Hotel.class);
        int bookedRooms = booking.getBookedRoomsNum();
        int numOfRoomsAvailable = hotel.getNumOfRoomsAvailable();


       // Check to see if there are available rooms
        if (numOfRoomsAvailable <= 0  || bookedRooms > numOfRoomsAvailable) {
            return false;
        } else {

            // update the hotel number of rooms available
            int numOfHotelRoomRemaining = numOfRoomsAvailable - bookedRooms;

           //Use WebClient from Spring WebFlux to Update the Hotels Table (Number of Rooms)
             Integer updatedRow = webClientBuilder.build()
                                .put()
                                .uri("http://hotel-service/api/hotels/update-num-of-rooms/" + hotelId)
                                .body(Mono.just(numOfHotelRoomRemaining), Hotel.class)
                                .retrieve()
                                .bodyToMono(Integer.class).block();

             int i = updatedRow.intValue();

             if (i == 0) {
                return false;
             }

             booking.setBookingStatus("booked");

            //create the booking
            bookingRepository.save(booking);

            return true;
        }



    }

    public Booking findBookingById(Long id) {
        return bookingRepository.findBookingById(id);
    }

    public List<Hotel> getUserBookedHotels(Long userId) {

        // Get the List of Bookings made by the user
        List<Booking> userBookings = bookingRepository.getUserBookedHotels(userId);

        // Loop through the list and get the hotels
        return userBookings.stream().map(booking -> {
            Hotel hotel = restTemplate.getForObject("http://hotel-service/api/hotels/" + booking.getHotelId(), Hotel.class);

            // return the list of hotels
            return new Hotel(hotel.getId(), hotel.getName(), hotel.getTotalNumOfRooms(), hotel.getNumOfRoomsAvailable(),
                    hotel.isRoomsAvailableStatus(), hotel.getUserId(), hotel.getLocation(), hotel.getPrice());
        }).collect(Collectors.toList());

    }




}
