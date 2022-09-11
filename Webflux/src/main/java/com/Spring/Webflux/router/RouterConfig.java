package com.Spring.Webflux.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.Spring.Webflux.handler.EmployeeHandler;

@Configuration
public class RouterConfig {
	
	@Autowired
	private EmployeeHandler empHandler;
	
	@Bean
	public RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route()
				.GET("/router/employees", empHandler::loadEmployees)
				.GET("/router/getEmployee/{input}", empHandler::loadEmployeeById)
				.POST("/router/employees/save", empHandler::saveEmployees)
				.build();			
	}

}
