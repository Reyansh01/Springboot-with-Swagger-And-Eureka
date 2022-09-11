package com.Spring.WebfluxApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Reyansh Project Spring Webflux",
		version = "1.0",
		description = "Implementing Swagger in Project")
)
public class WebfluxAppApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(WebfluxAppApplication.class, args);
	}

}
