package com.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.proyecto.model")
@EnableJpaRepositories(basePackages = "com.proyecto.repository")
@SpringBootApplication
public class MsParticipantesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsParticipantesApplication.class, args);
	}

}
