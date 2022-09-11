package com.Spring.WebfluxApp.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Spring.WebfluxApp.Dao.PersonRepository;
import com.Spring.WebfluxApp.Entity.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


//implements ReactiveUserDetailsService 
@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepo;

	
	public Flux<Person> getPersons(int pagesize, int offset) {
		return personRepo.findAll(pagesize, offset);	
	}
	
	public Flux<Person> getPersonsSortedByName(int pagesize, int offset) {
		return personRepo.findAllSortedByName(pagesize, offset);	
	}
	
	public Flux<Person> getPersonsSortedByID(int pagesize, int offset) {
		return personRepo.findAllSortedByDecID(pagesize, offset);	
	}
	
//	public Flux<Person> getPersonsTemp(int pagesize) {
//		return personRepo.findAllTemp(pagesize);	
//	}
	

	public Mono<Person> save(Person person) {
		return personRepo.createPerson(person);
	}
	
	public Mono<Person> getById(long id) {
		return personRepo.findById(id);
	}

	public Mono<Person> updatePerson(Person person) {
		return personRepo.updatePerson(person);
	}

	public Flux<Person> getPersonsWithoutDelay(int pagesize, int offset) {
		return personRepo.findAllWithoutDelay(pagesize, offset);
	}

}
