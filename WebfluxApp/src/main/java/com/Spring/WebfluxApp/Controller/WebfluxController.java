package com.Spring.WebfluxApp.Controller;



import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Spring.WebfluxApp.Entity.Person;
import com.Spring.WebfluxApp.Service.PersonService;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@SecurityScheme(
		name = "Reyansh Authentication",
		type = SecuritySchemeType.HTTP,
		scheme = "basic"
//		in = SecuritySchemeIn.HEADER
)
public class WebfluxController {
	
//	public static int OFFSET = 0;
	
	@Autowired
	PersonService personService;

	
	@PostMapping(value = "/save")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "save Person in Database",
	security = @SecurityRequirement(name = "Reyansh Authentication"),
	tags = {"Add/Update in Database"})
//	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Mono<Person> savePerson(@RequestBody Person person) {
		return personService.save(person).thenReturn(person);
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@Operation(summary = "Get data from database",
	security = @SecurityRequirement(name = "Reyansh Authentication"),
	tags = {"Get from Database"})
	@GetMapping(value = "/getAll", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	@ResponseStatus(code = HttpStatus.FOUND)
	public Flux<Person> getPerson(@RequestParam(value = "pageno", defaultValue = "1") int pageno,
			@RequestParam(value = "pagesize", defaultValue = "4") int pagesize) {
		int offset = (pageno - 1) * pagesize;
//		OFFSET += (pageno - 1) * pagesize;
//		return personService.getPersonsTemp(pagesize);
		return personService.getPersons(pagesize, offset);		
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@Operation(summary = "Get data from database without any delay",
	security = @SecurityRequirement(name = "Reyansh Authentication"),
	tags = {"Get from Database"})
	@GetMapping(value = "/getAllWithoutDelay", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	@ResponseStatus(code = HttpStatus.FOUND)
	public Flux<Person> getPersonWithoutDelay(@RequestParam(value = "pageno", defaultValue = "1") int pageno,
			@RequestParam(value = "pagesize", defaultValue = "4") int pagesize) {
		int offset = (pageno - 1) * pagesize;
//		OFFSET += (pageno - 1) * pagesize;
//		return personService.getPersonsTemp(pagesize);
		return personService.getPersonsWithoutDelay(pagesize, offset);		
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@Operation(summary = "Get Person by ID",
	security = @SecurityRequirement(name = "Reyansh Authentication"),
	tags = {"Get from Database"})
//	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping(value = "/getByID/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Mono<Person> getPersonById(@PathVariable("id") long iD) {
		return personService.getById(iD);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Update Person",
	security = @SecurityRequirement(name = "Reyansh Authentication"),
	tags = {"Add/Update in Database"})
	@PutMapping(value = "/update")
//	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Mono<Person> updatePerson(@RequestBody Person person) {
		return personService.updatePerson(person).thenReturn(person);
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@Operation(summary = "Get data sorted by name",
	security = @SecurityRequirement(name = "Reyansh Authentication"),
	tags = {"Get from Database"})
	@GetMapping(value = "/getAllSortedByName", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	@ResponseStatus(code = HttpStatus.FOUND)
	public Flux<Person> getPersonSortedByName(@RequestParam(value = "pageno", defaultValue = "1") int pageno,
			@RequestParam(value = "pagesize", defaultValue = "4") int pagesize) {
		int offset = (pageno - 1) * pagesize;
//		OFFSET += (pageno - 1) * pagesize;
//		return personService.getPersonsTemp(pagesize);
		return personService.getPersonsSortedByName(pagesize, offset);
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@Operation(summary = "get data sorted by decreasing order of ID",
	security = @SecurityRequirement(name = "Reyansh Authentication"),
	tags = {"Get from Database"})
	@GetMapping(value = "/getAllSortedByDecID", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	@ResponseStatus(code = HttpStatus.FOUND)
	public Flux<Person> getPersonSortedByID(@RequestParam(value = "pageno", defaultValue = "1") int pageno,
			@RequestParam(value = "pagesize", defaultValue = "4") int pagesize) {
		int offset = (pageno - 1) * pagesize;
//		OFFSET += (pageno - 1) * pagesize;
//		return personService.getPersonsTemp(pagesize);
		return personService.getPersonsSortedByID(pagesize, offset);		
	}
	
}
