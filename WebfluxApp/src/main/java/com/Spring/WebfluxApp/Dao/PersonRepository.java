package com.Spring.WebfluxApp.Dao;

import java.time.Duration;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.Spring.WebfluxApp.Entity.Person;
//import com.Spring.WebfluxApp.Controller.WebfluxController;

//import com.Spring.WebfluxApp.Entity.Person;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PersonRepository {
	
//	ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
//		    .option(DRIVER, "mysql")
//		    .option(HOST, "127.0.0.1")
//		    .option(USER, "root")
//		    .option(PORT, 3306)  // optional, default 3306
//		    .option(PASSWORD, "database-password-in-here") // optional, default null, null means has no password
//		    .option(DATABASE, "r2dbc")
//		    .build();
//		ConnectionFactory connectionFactory1 = ConnectionFactories.get(options);
//		
//		Mono<Connection> connectionMono = Mono.from(connectionFactory1.create());

		MySqlConnectionConfiguration configuration = MySqlConnectionConfiguration.builder()
			    .host("127.0.0.1")
			    .user("root")
			    .port(3306) // optional, default 3306
			    .password("REYANSHMISHRA") // optional, default null, null means has no password
			    .database("WebfluxDB") // optional, default null, null means not specifying the database
			    .build();
		
		ConnectionFactory connectionFactory = MySqlConnectionFactory.from(configuration);
		
		Mono<Connection> connectionMono = Mono.from(connectionFactory.create());
		
		@Autowired
		DatabaseClient dbClient;
		
//		public PersonRepository() {
//			dbClient.sql("CREATE TABLE hello IF NOT EXISTS (ID INT NOT NULL PRIMARY KEY "
//					+ "NAME VARCHAR(255) MOBNO VARCHAR(255));").fetch();
//		}
		
//		private final String createTable = "CREATE TABLE IF NOT EXISTS Person ID Long,"
//				+ "NAME varchar(255), MOBNO varchar(25%); ";
		
		private final String insertPerson = "INSERT INTO Person (ID, NAME, MOBNO)"
				+ "VALUES(?, ?, ?);";

		
		public static final BiFunction<Row, RowMetadata, Person> MAPPING_FUNCTION = 
				(row, rowMetaData) -> Person.builder()
	            .id(row.get("id", Long.class))
	            .name(row.get("name", String.class))
	            .mobNo(row.get("mobNo", String.class))
	            .build();
		
		
		public Mono<Person> createPerson(Person person) {
			
			return (Mono<Person>) dbClient.sql(insertPerson)
//	                .filter((statement, executeFunction) -> statement.returnGeneratedValues("ID")
//	                		.execute())
	                .bind(0, person.getId())
	                .bind(1, person.getName())
	                .bind(2, person.getMobNo())
	                .map(MAPPING_FUNCTION)
	                .first();
//	                .first()
//	                .doOnNext(i -> System.out.println("Person added: " + i))
//	                .delayElement(Duration.ofSeconds(1));
//	                .map(r -> (Long) r.getId()).toString();
		}
		
//		private final String findPerson = "SELECT * FROM Person where LIMIT {:pagesize} "
//			+ "OFFSET {:offset} ;";
		
		public Flux<Person> findAll(int pagesize, int offset) {
			return (Flux<Person>) dbClient.sql("SELECT * FROM Person LIMIT ? OFFSET ?;")
					.bind(0, pagesize)
					.bind(1, offset)
//					.filter((statement, executeFunction) -> statement.fetchSize(10).execute())
//					.map(i -> (Long) i.get("ID"))
//					.all();
					.map(MAPPING_FUNCTION)
					.all()
//					.limitRequest(2)
					.delayElements(Duration.ofSeconds(1))
					.doOnNext(i -> System.out.println("value: " + i))
//					.publishOn(Schedulers.parallel())
					.log();
//			return 
//			dbClient.sql(findPerson)
//					.bind(0, "ID")
//					.bind(1, "NAME")
//					.bind(2, "MOBNO")
//					.fetch();
//			return null;
			
//			return Flux..sql(findPerson)
//		return Flux.interval(Duration.ofSeconds(1))
//				.sql(findPerson)
//				.map(i -> new Person.sql(findPerson))
//				.fetch();
		}
		
		public Flux<Person> findAllSortedByName(int pagesize, int offset) {
			return (Flux<Person>) dbClient.sql("SELECT * FROM Person LIMIT ? OFFSET ?;")
					.bind(0, pagesize)
					.bind(1, offset)
//					.filter((statement, executeFunction) -> statement.fetchSize(10).execute())
//					.map(i -> (Long) i.get("ID"))
//					.all();
					.map(MAPPING_FUNCTION)
					.all()
					.delayElements(Duration.ofSeconds(1))
					.doOnNext(i -> System.out.println("value: " + i))
					.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		}
		
		public Flux<Person> findAllSortedByDecID(int pagesize, int offset) {
			return (Flux<Person>) dbClient.sql("SELECT * FROM Person ORDER BY ID DESC "
					+ "LIMIT ? OFFSET ?;")
					.bind(0, pagesize)
					.bind(1, offset)
//					.filter((statement, executeFunction) -> statement.fetchSize(10).execute())
//					.map(i -> (Long) i.get("ID"))
//					.all();
					.map(MAPPING_FUNCTION)
					.all()
					.delayElements(Duration.ofSeconds(1))
					.doOnNext(i -> System.out.println("value: " + i));
		}
		
		
//		public Flux<Person> findAllTemp(int pagesize) {
//			return (Flux<Person>) dbClient.sql("SELECT * FROM Person LIMIT ? OFFSET ?;")
//					.bind(0, pagesize)
//					.bind(1, WebfluxController.OFFSET)
////					.filter((statement, executeFunction) -> statement.fetchSize(10).execute())
////					.map(i -> (Long) i.get("ID"))
////					.all();
//					.map(MAPPING_FUNCTION)
//					.all()
//					.delayElements(Duration.ofSeconds(1))
//					.doOnNext(i -> System.out.println("value: " + i));
//		}
		
	
		public Mono<Person> findById(long id) {
			return dbClient.sql("SELECT * FROM Person WHERE ID=?;")
					.bind(0, id)
//					.fetch().first()
					.map(MAPPING_FUNCTION)
					.first()
					.doOnNext(i -> System.out.println("value for ID " + i))
					.delayElement(Duration.ofSeconds(1));
//					.log();			
		}

		public Mono<Person> updatePerson(Person person) {
			return dbClient.sql("UPDATE Person SET NAME = ?, MOBNO = ? "
					+ "WHERE ID = ?;")
					.bind(0, person.getName())
					.bind(1, person.getMobNo())
					.bind(2, person.getId())
					.map(MAPPING_FUNCTION)
					.first();
		}

		public Flux<Person> findAllWithoutDelay(int pagesize, int offset) {
			return dbClient.sql("SELECT * FROM Person LIMIT ? OFFSET ?;")
					.bind(0, pagesize)
					.bind(1, offset)
//					.filter((statement, executeFunction) -> statement.fetchSize(10).execute())
//					.map(i -> (Long) i.get("ID"))
//					.all();
					.map(MAPPING_FUNCTION)
					.all()
//					.limitRequest(2)
					.doOnNext(i -> System.out.println("value: " + i))
//					.publishOn(Schedulers.parallel())
					.log();
		}
		
}
