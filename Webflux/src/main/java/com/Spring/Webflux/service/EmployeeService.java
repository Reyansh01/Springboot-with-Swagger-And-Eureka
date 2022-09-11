package com.Spring.Webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;


import com.Spring.Webflux.dao.EmployeeDao;
import com.Spring.Webflux.dto.EmployeeDTO;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;

import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeDao empDao;
	
	public Flux<EmployeeDTO> getDetails() {
		long start = System.currentTimeMillis();
		Flux<EmployeeDTO> employees = empDao.getEmployee();
		long end = System.currentTimeMillis();
		System.out.println("Total execution time is: " + (end-start));
		return employees;		
	}

	public Flux<EmployeeDTO> getDetailsAfterSomeTime(int limit) {
//		long start = System.currentTimeMillis();
		Flux<EmployeeDTO> employees = empDao.getEmployeeAfterSomeTime(limit);
//		long end = System.currentTimeMillis();
//		System.out.println("Total execution time is: " + (end-start));
		return employees;		
	}
	
//	public Mono<Object> getDetailsPage(Pageable page, int limit) {
//		
////		Map<String, String> map = new HashMap<>();
//		
////		Page<EmployeeDTO> employeePage = empDao.findAll(page);
////		map.put("data", employeePage.getContent());
////		map.put("Total No. of Pages", employeePage.getTotalPages());
////		map.put("Current Page", employeePage.getNumber());
////		return map;
//		return empDao.findAll(limit)
//				.collectList()
//				.map(m -> (
//						m.stream()
//						.skip(page.getPageNumber() * page.getPageSize())
//						.limit(limit)));
////						.collect(Collectors.toList()), 
////						page.getPageNumber(), page.getPageSize(), m.size()));
//	}

}
