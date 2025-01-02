package com.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@EntityScan(basePackages = "com.proyecto.model")
@SpringBootApplication
public class MsGestionClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGestionClienteApplication.class, args);
	}
	
	@Bean

	   RestTemplate getTemplate(){

	        return new RestTemplate();

	}


}
