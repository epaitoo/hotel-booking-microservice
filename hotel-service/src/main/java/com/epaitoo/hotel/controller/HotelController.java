package com.epaitoo.hotel.controller;

import com.epaitoo.hotel.VO.ResponseTemplateVO;
import com.epaitoo.hotel.entity.Hotel;
import com.epaitoo.hotel.service.HotelService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotels")
@Slf4j
public class HotelController {

    @Autowired
    private HotelService hotelService;

    /**
     * Returns a Lists of Hotels
     * @return List of Hotels
     */
    @GetMapping
    public List<Hotel> findAllHotels() {
        log.info("Inside findAllHotels of HotelController");
        return hotelService.findAll();
    }

    /**
     * Create a Hotel
     * @param hotel hotel
     * @return the new hotel
     */
    @PostMapping
    public Hotel saveHotel(@RequestBody Hotel hotel) {
        log.info("Inside saveHotel of HotelController");
        return hotelService.saveHotel(hotel);
    }

    /**
     * Get a Hotel
     * @param id of the hotel
     * @return a single hotel resource
     */
    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable("id") Long id) {
        log.info("Inside getHotelById of HotelController");
        return hotelService.findHotelById(id);
    }

    /**
     * Updates a Hotel
     * @param id of the hotel
     * @param newHotel information
     * @return newHotel Information
     */
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable("id") Long id, @RequestBody Hotel newHotel) {
        log.info("Inside updateHotel of HotelController");
        Optional<Hotel> updateHotel = hotelService.updateHotel(id, newHotel);
        return updateHotel.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                   Hotel created = hotelService.saveHotel(newHotel);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getUserId())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });

    }

    /**
     * Deletes a Hotel
     * @param id of the hotel
     * @return noContent Response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Hotel> delete(@PathVariable("id") Long id) {
        log.info("Inside delete of HotelController");
        hotelService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get the hotel and its User information
     * @param id of the hotel
     * @return the hotel and its user
     */
    @GetMapping("/user/{id}")
    @CircuitBreaker(name = "user-service", fallbackMethod = "userServiceFallBackMethod")
    @Retry(name = "user-service", fallbackMethod = "userServiceFallBackMethod")
    public ResponseEntity<ResponseTemplateVO> getHotelWithUserInfo(@PathVariable("id") Long id){
        return ResponseEntity.ok(hotelService.getHotelWithUserInfo(id));
    }

    /**
     * Update the number of Room for a hotel
     * @param id of the hotel
     * @param newRoomsNum
     * @return the new number of rooms or 0 if room num is less than 0
     *
     */
    @PutMapping("/update-num-of-rooms/{id}")
    public ResponseEntity<Integer> updateNumOfRooms(@PathVariable("id") Long id, @RequestBody int newRoomsNum) {
        int rowUpdated = hotelService.updateNumOfRoomsAvailable(id, newRoomsNum);
        if (rowUpdated > 0) {
            return ResponseEntity.ok(rowUpdated);
        }
        return ResponseEntity.badRequest().body(0);
    }


    /**
     * Fallback Method for getHotelWithUserInfo method
     * @param e Exception
     * @return a bad request response
     */
    public ResponseEntity<ResponseTemplateVO> userServiceFallBackMethod(Exception e) {
        return ResponseEntity.badRequest().body(hotelService.hotelsWithUserInfoFallBackMethod());
    }







}
