package com.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
@EntityScan(basePackages = "com.proyecto.model")
@EnableJpaRepositories(basePackages = "com.proyecto.repository")
@SpringBootApplication(scanBasePackages = {"com.proyecto.controller","com.proyecto.service"})
public class MsGestionCliente3Application {

	public static void main(String[] args) {
		SpringApplication.run(MsGestionCliente3Application.class, args);
	}
	@Bean

	   RestTemplate getTemplate(){

	        return new RestTemplate();

	}
}
