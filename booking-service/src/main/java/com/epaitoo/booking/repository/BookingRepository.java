package com.epaitoo.booking.repository;

import com.epaitoo.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findBookingById(Long id);

    @Transactional
    @Modifying
    @Query("SELECT b FROM Booking b WHERE b.userId = :userId")
    List<Booking> getUserBookedHotels(@Param(value = "userId") Long userId);
}
