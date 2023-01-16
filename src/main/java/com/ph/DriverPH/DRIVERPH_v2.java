package com.ph.DriverPH;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication(scanBasePackages = {"com.ph.DriverPH"})
public class DRIVERPH_v2 {

	public static void main(String[] args) {
		SpringApplication.run(DRIVERPH_v2.class, args);
		log.info("DRIVERPH SERVER IS RUNNING...");
	}

}
