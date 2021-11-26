package com.epaitoo.hotel.service;

import com.epaitoo.hotel.VO.ResponseTemplateVO;
import com.epaitoo.hotel.VO.User;
import com.epaitoo.hotel.entity.Hotel;
import com.epaitoo.hotel.repository.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RestTemplate restTemplate;


    public List<Hotel> findAll() {
        log.info("Inside findAll of HotelService");
        return hotelRepository.findAll();
    }

    public Hotel saveHotel(Hotel hotel) {
        log.info("Inside saveHotel of HotelService");
        return hotelRepository.save(hotel);
    }

    public Hotel findHotelById(Long id) {
        log.info("Inside findHotelById of HotelService");
        return hotelRepository.findHotelById(id);
    }

    public Optional<Hotel> updateHotel(Long id, Hotel newHotel) {
        log.info("Inside updateUser of HotelService");
        return hotelRepository.findById(id)
                .map(oldHotel -> {
                   Hotel updatedHotel = oldHotel.updateWith(newHotel);
                   return hotelRepository.save(updatedHotel);
                });
    }


    public void delete(Long id) {
        log.info("Inside delete of HotelService");
        hotelRepository.deleteById(id);
    }

    /**
     * get user and hotel info
     * @param id of the hotel
     * @return the ResponseTemplateVO object
     */
    public ResponseTemplateVO getHotelWithUserInfo(Long id) {
        log.info("Inside getHotelWithUserInfo of HotelService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Hotel hotel = hotelRepository.findHotelById(id);

        User user = restTemplate.getForObject("http://users-service/api/users/" + hotel.getUserId(),
                User.class);
        vo.setUser(user);
        vo.setHotel(hotel);

        return vo;
    }

    /**
     * Update the number of rooms
     * @param id of the hotel
     * @param numOfRoom
     * @return new number of rooms for the hotel
     */
    public int updateNumOfRoomsAvailable(long id, int numOfRoom) {
        return hotelRepository.setNumOfRoomsAvailable(id, numOfRoom);
    }

    public ResponseTemplateVO hotelsWithUserInfoFallBackMethod(){
        log.info("Inside hotelsWithUserInfoFallBackMethod of HotelService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        vo.setUser(null);
        vo.setHotel(null);

        return vo;
    }





}
