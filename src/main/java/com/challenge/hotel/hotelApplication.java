package com.challenge.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class hotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(hotelApplication.class, args);
	}
}
