package com.challenge.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableWebMvc
//@EntityScan(
//		  basePackageClasses = { hotelApplication.class, Jsr310JpaConverters.class }
//		)

@SpringBootApplication
@EnableJpaAuditing
public class hotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(hotelApplication.class, args);
	}
}
