package com.mouad.Taches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TachesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TachesApplication.class, args);
	}

}
