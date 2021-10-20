package com.epaitoo.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "total_num_of_rooms")
    private Integer totalNumOfRooms;

    @Column(name = "num_of_rooms_available")
    private Integer numOfRoomsAvailable;

    @Column(name = "room_available_status", columnDefinition = "boolean default true")
    private boolean roomsAvailableStatus;

    private Long userId;
    private String location;
    private BigDecimal price;


    public Hotel updateWith(Hotel hotel) {
        return new Hotel(this.id, hotel.name, hotel.totalNumOfRooms, hotel.numOfRoomsAvailable, hotel.roomsAvailableStatus,
                hotel.userId, hotel.location, hotel.price);
    }

}
