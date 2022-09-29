package com.ph.DriverPH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {"com.ph.DriverPH"})

public class SpringBootRestApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootRestApplication.class, args);
	}

}
