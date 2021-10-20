package com.epaitoo.hotel.booking.service.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class HotelBookingServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingServiceRegistryApplication.class, args);
	}

}
