package com.Spring.Webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Spring.Webflux.dto.EmployeeDTO;
import com.Spring.Webflux.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;

@RestController
@SecurityScheme(
		name = "Reyansh Authentication",
		type = SecuritySchemeType.HTTP,
		scheme = "basic"
//		in = SecuritySchemeIn.HEADER
)
public class FluxController {
	
	@Autowired
	EmployeeService empService;
	
	@Operation(summary = "get from Database",
			security = @SecurityRequirement(name = "Reyansh Authentication"),
			tags = {"get in Database"})
	@GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<EmployeeDTO> getAllEmployees() {
		return empService.getDetails();
	}
	
//	@GetMapping(value="flux/pagination", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	public Mono<Object> getAllEmployeesPage(@RequestParam(name = "pageno", defaultValue = "0") int pageNo,
//			@RequestParam(name = "pagesxize", defaultValue = "50") int pageSize, 
//			@RequestParam(name = "limit", defaultValue = "10") int limit) {
//		
//		return empService.getDetailsPage(PageRequest.of(pageNo, pageSize), limit);
//	}

	@Operation(summary = "get from Database",
			security = @SecurityRequirement(name = "Reyansh Authentication"),
			tags = {"get in Database"})
	@GetMapping(value="flux/aftersometime", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<EmployeeDTO> getAllEmployeesAfterSomeTime(@RequestParam(name = "limit", defaultValue = "20") int limit) {
		return empService.getDetailsAfterSomeTime(limit);
	}
	
}
