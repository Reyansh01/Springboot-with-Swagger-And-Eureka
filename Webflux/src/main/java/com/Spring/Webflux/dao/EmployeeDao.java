package com.Spring.Webflux.dao;

import java.time.Duration;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.Spring.Webflux.dto.EmployeeDTO;

import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;

@Component
public class EmployeeDao {

	public Flux<EmployeeDTO> getEmployee() {
		
		return Flux.range(1, 20)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("processing count number is : " + i))
				.map(i -> new EmployeeDTO(i, "employee" + i));
		
	}
	
	public Flux<EmployeeDTO> getEmployeeWithoutDelay() {
		
		return Flux.range(1, 20)
				.doOnNext(i -> System.out.println("processing count number is : " + i))
				.map(i -> new EmployeeDTO(i, "employee" + i));
		
	}

	public Flux<EmployeeDTO> findAll(int limit) {
		return Flux.range(1, 20)
//				.doOnNext(i -> System.out.println("PAGINATION : " + i))
//				.limitRequest(limit)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("pagination " + i))
				.map(i -> new EmployeeDTO(i, " employee " + i))
//				.delayElements(Duration.ofSeconds(limit))
				.limitRequest(limit);
	}

	public Flux<EmployeeDTO> getEmployeeAfterSomeTime(int limit) {
		return Flux.range(1, 100)
//				.doOnNext(i -> System.out.println("PAGINATION : " + i))
//				.limitRequest(limit)
				.doOnNext(i -> System.out.println("pagination " + i))
				.delayElements(Duration.ofSeconds(1))
//				.doOnNext(i -> System.out.println("pagination " + i))
				.map(i -> new EmployeeDTO(i, " employee " + i))
//				.delayElements(Duration.ofSeconds(limit))
				.limitRequest(limit);
		
	}
	
}
