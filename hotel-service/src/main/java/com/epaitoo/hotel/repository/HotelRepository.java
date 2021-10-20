package com.epaitoo.hotel.repository;

import com.epaitoo.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findHotelById(Long id);

    @Transactional
    @Modifying
    @Query("update Hotel h set h.numOfRoomsAvailable = :numOfRoomsAvailable where h.id = :id")
    int setNumOfRoomsAvailable(@Param(value = "id") long id, @Param(value = "numOfRoomsAvailable") int numOfRoomsAvailable);


}
