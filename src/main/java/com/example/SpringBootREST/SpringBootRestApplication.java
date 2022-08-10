package com.example.SpringBootREST;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {"com.example.SpringBootREST"})

public class SpringBootRestApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootRestApplication.class, args);
	}

}
