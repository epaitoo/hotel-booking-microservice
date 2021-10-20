package com.epaitoo.booking.controller;

import com.epaitoo.booking.VO.Hotel;
import com.epaitoo.booking.entity.Booking;
import com.epaitoo.booking.service.BookingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@Slf4j
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> findAllBookings() {
        log.info("Inside findAllBookings of BookingController");
        return bookingService.findAll();
    }

    @PostMapping("/add-booking/{hotel-id}")
    @CircuitBreaker(name = "hotel-service", fallbackMethod = "addBookingFallBackMethod")
    @Retry(name = "hotel-service", fallbackMethod = "addBookingFallBackMethod")
    public ResponseEntity<String> addBooking(@PathVariable("hotel-id") Long hotelId, @RequestBody Booking booking) {
        log.info("Inside addBooking of BookingController");
        boolean hasNewBooking = bookingService.saveBooking(hotelId, booking);
        if (hasNewBooking) {
            return ResponseEntity.ok("Hotel Booked Successfully");
        } else {
            return ResponseEntity.badRequest().body("Could not Book Hotel. Please try again!");
        }
    }



    @GetMapping("/{id}")
    public Booking findABooking(@PathVariable("id") Long id) {
        log.info("Inside findABooking of BookingController");
        return bookingService.findBookingById(id);
    }

    @GetMapping("/user-bookings/{user-id}")
    @CircuitBreaker(name = "hotel-service", fallbackMethod = "hotelServiceFallBackMethod")
    @Retry(name = "hotel-service", fallbackMethod = "hotelServiceFallBackMethod")
    public ResponseEntity<List<Hotel>> getUserBookedHotels(@PathVariable("user-id") Long userId) {
        log.info("Inside getUserBookedHotels of BookingController");
        return ResponseEntity.ok(bookingService.getUserBookedHotels(userId));

    }

    // FallBack Method for getUserBookedHotels
    public ResponseEntity<List<Hotel>> hotelServiceFallBackMethod(Exception e) {
        log.info("Response From hotelServiceFallBackMethod");
        return ResponseEntity.badRequest().body(null);
    }

    public ResponseEntity<String> addBookingFallBackMethod(Exception e){
        log.info("Response From addBookingFallBackMethod");
        return ResponseEntity.badRequest().body("Hotel Service is down." +
                "Cannot add Booking at this time");
    }




}
