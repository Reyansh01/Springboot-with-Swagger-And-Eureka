package com.Spring.WebfluxApp;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.BodyInserters;

import com.Spring.WebfluxApp.Controller.WebfluxController;
import com.Spring.WebfluxApp.Dao.PersonRepository;
import com.Spring.WebfluxApp.Entity.Person;
import com.Spring.WebfluxApp.Service.PersonService;
import com.google.common.net.HttpHeaders;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;


//@WebAppConfiguration
//@SpringBootTest

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = WebfluxController.class)
@Import(PersonService.class)
class WebfluxAppApplicationTests {

	@Test
//	@Disabled
	void contextLoads() {
	}
	
	@MockBean
	PersonRepository personRepository;
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void testCreatePerson() {
		Person person = new Person();
		person.setId(33);
		person.setName("Ojas Naik");
		person.setMobNo("99723894722");
		
		Mockito.when(personRepository.createPerson(person))
		.thenReturn(Mono.just(person));
		
		webTestClient.post()
		.uri("/save")
		.contentType(MediaType.APPLICATION_JSON)
		.body(BodyInserters.fromObject(person))
		.exchange()
		.expectStatus().isCreated();
		
		Mockito.verify(personRepository, times(1)).createPerson(person);
	}
	
	
	@Test
	void testGetPersonById() {
		Person person = new Person();
		person.setId(33);
		person.setName("Ojas Naik");
		person.setMobNo("99723894722");
		
		Mockito.when(personRepository.findById(33))
		.thenReturn(Mono.just(person));
		
		webTestClient.get()
		.uri("/getByID{id}", 33).exchange()
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$.name").isNotEmpty()
		.jsonPath("$.id").isEqualTo(33)
		.jsonPath("$.name").isEqualTo("Ojas Naik")
		.jsonPath("$.mobNo").isEqualTo("99723894722");
		
		Mockito.verify(personRepository, times(1)).findById(33);
	}
	
//	@Test
//	void testGetPersonById() {
//		Person person = new Person();
//		person.setId(33);
//		person.setName("Ojas Naik");
//		person.setMobNo("99723894722");
//		
//		List<Person> list = new ArrayList<Person>();
//		list.add(person);
//		
//		Flux<Person> personFlux = Flux.fromIterable(list);
//		
//		Mockito.when(personRepository.findById(33))
//		.thenReturn(personFlux);
//		
//		webTestClient.get().uri("/id/{id}", 33)
//		.header(HttpHeaders.ACCEPT, "application/json")
//		.exchange()
//		.expectStatus().isOk()
//		.expectBodyList(Person.class);
//		
//		Mockito.verify(personRepository, times(1)).findById(33);
//		
//	}
	
	
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	private MockMvc mockMvc;
//	
//	@BeforeEach
//	private void setup() throws Exception {
//		this.mockMvc = MockMvcBuilders
//				.webAppContextSetup(this.webApplicationContext)
//				.build();
//	}
//	
//	@Test
//	public void createPerson() throws Exception {
//		String payload = "{\n"
//				+ "    \"id\": 33,\n"
//				+ "    \"name\": \"Ojas Naik\",\n"
//				+ "    \"mobNo\": \"998979989\"\n"
//				+ "}";
//		
//		mockMvc.perform(post("/save")
//				.contentType(MediaType.TEXT_EVENT_STREAM_VALUE)
//				.content(payload))
//		.andExpect(status().isOk())
//		.andReturn();
//	}
//	
//	@Test
//	private void fetchPerson() throws Exception {
//		mockMvc.perform(get("/getByID/{id}",1L))
//		.andExpect(status().isOk())
//		.andReturn();
//	}
	
}
