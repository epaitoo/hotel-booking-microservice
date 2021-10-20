package com.epaitoo.booking.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private Long id;
    private String name;
    private Integer totalNumOfRooms;
    private Integer numOfRoomsAvailable;
    private boolean roomsAvailableStatus;
    private Long userId;
    private String location;
    private BigDecimal price;



}
