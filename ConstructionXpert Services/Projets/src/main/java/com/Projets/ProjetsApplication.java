package com.mouad.Projets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProjetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetsApplication.class, args);
	}

}
