package com.epaitoo.hotel.VO;


import com.epaitoo.hotel.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private Hotel hotel;
    private User user;
}
