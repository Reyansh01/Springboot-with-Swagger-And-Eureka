//package com.Spring.WebfluxApp.Dao;
//
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.time.Duration;
//import java.util.Map;
//import java.util.Properties;
//import java.util.function.BiFunction;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
//import org.springframework.data.r2dbc.repository.Query;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import com.Spring.WebfluxApp.Entity.Person;
//
//import org.springframework.r2dbc.core.DatabaseClient;
//import org.springframework.r2dbc.core.DatabaseClient.GenericExecuteSpec;
//import org.springframework.r2dbc.core.FetchSpec;
//
//import io.r2dbc.spi.Connection;
//import io.r2dbc.spi.ConnectionFactory;
//import io.r2dbc.spi.Row;
//import io.r2dbc.spi.RowMetadata;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//
//@Repository
////@EnableR2dbcRepositories
//public class PersonDao {
//	
//	private final DatabaseClient dbClient = null;
//	
////	@Autowired
////    public PersonDao(ConnectionFactory connectionFactory) {
////        dbClient = DatabaseClient.builder()
////                .connectionFactory(connectionFactory)
////                .build();
////    }
//	
//	@Bean
//	DatabaseClient dbClient() throws ClassNotFoundException, SQLException {
//		return DatabaseClient.create(connectionFactory());
//	}
//	
//	@Bean
//	ConnectionFactory connectionFactory() {
//		PostgreSqlConnectionConfiguration config ;
//	}
//	
////	@Bean
////	ConnectionFactory connectionFactory1() throws ClassNotFoundException, SQLException {
////		try {
////			Class.forName("com.mysql.cj.jdbc.Driver");
////		} catch (ClassNotFoundException e) {
////			System.out.println("Class not found.......");
////			e.printStackTrace();
////		}
////        try {
////        	Properties prop = new java.util.Properties();
////        	prop.put("user", "root");
////        	prop.put("password","REYANSHMISHRA");
////        	return (ConnectionFactory) DriverManager.getConnection("jdbc:mysql://localhost:3306/WebfluxDB", prop);
////        	return (ConnectionFactory) conn;
////        	Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/WebfluxDB");
////        	return (ConnectionFactory) c;
////			return (ConnectionFactory) DriverManager.getConnection("jdbc:mysql://localhost:3306/WebfluxDB", prop);
////		} catch (SQLException e) {
////			System.out.println("SQL Exception occured.......");
////
////			e.printStackTrace();
////		}
////	}
//	
////	private static java.sql.Connection conn;
////	
////	@Bean
////	public ConnectionFactory connectionFactory() {
////		try {
////			Class.forName("com.mysql.cj.jdbc.Driver");
////		} catch (ClassNotFoundException e) {
////			System.out.println("Class not found.......");
////			e.printStackTrace();
////		}
////        try {
////        	Properties prop = new java.util.Properties();
////        	prop.put("user", "root");
////        	prop.put("password","REYANSHMISHRA");
////        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WebfluxDB", prop);
////        	return (ConnectionFactory) conn;
//////        	Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/WebfluxDB");
//////        	return (ConnectionFactory) c;
//////			return (ConnectionFactory) DriverManager.getConnection("jdbc:mysql://localhost:3306/WebfluxDB", prop);
////		} catch (SQLException e) {
////			System.out.println("SQL Exception occured.......");
////
////			e.printStackTrace();
////		} 
////        return null;
////	}
////	
////	@Bean
////	DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
////	    return DatabaseClient.builder()
////	        .connectionFactory(connectionFactory)
////	        .namedParameters(true)
////	        .build();
////	}
//	
////	public PersonDao() {
////		return dbClient.sql("CREATE TABLE Person(ID int not null PRIMARY KEY, "
////				+ "NAME varchar(255), MOBNO varchar(255) ").fetch();
////	}
//	
//	private final static String insertPerson = "INSERT INTO Person (ID, NAME, MOBNO)"
//			+ "VALUES(?, ?, ?);";
//	
//	public Mono<Integer> createPerson(Person person) {
//		return dbClient.sql(insertPerson)
//                .filter((statement, executeFunction) -> statement.returnGeneratedValues("ID")
//                		.execute())
////                .bind(0, person.getId())
//                .bind(0, person.getName())
//                .bind(1, person.getMobNo())
//                .fetch().first()
//                .map(r -> (Integer) r.get("ID"));
//	}
//		
////	public Flux<Person> getAllperson() {
////		return Flux.interval(Duration.ofSeconds(1))
////				.map(i -> new Person.sql(findPerson))
//////				.sql(findPerson)
////				.fetch());
////	}
//	
//	public Mono<Integer> save(Person p) {
//        return dbClient.sql("INSERT INTO  Person (ID, NAME, MOBNO) VALUES (:ID, :NAME, :MOBNO)")
//                .filter((statement, executeFunction) -> statement.returnGeneratedValues("ID").execute())
////                .bind("ID", p.getId())
//        		.bind("NAME", p.getName())
//                .bind("MOBNO", p.getMobNo())
//                .fetch()
//                .first()
//                .map(r -> (Integer) r.get("ID"));
//    }
//	
//
////	static String findPerson = "SELECT * FROM Person where LIMIT pagesize"
////			+ "OFFSET offset;";
//	
////	public static final BiFunction<Row, RowMetadata, Person> MAPPING_FUNCTION = (row, rowMetaData) -> DatabaseClient.builder()
////			.id(row.get("ID", Integer.class))
////            .name(row.get("NAME", String.class))
////            .mobNo(row.get("MOBNO", String.class))
////            .build();
//	
////	@Query(value = "SELECT * FROM Person where"
////			+ "LIMIT : pagesize OFFSET : offset;")
//	public Flux<Person> findAll(int pagesize, int offset) {
////		return Flux.just(dbClient.sql("SELECT * FROM Person where"
////			+ "LIMIT (:pagesize) OFFSET (:offset)")
////				.filter((statement, executeFunction) -> statement.fetchSize(10).execute())
////				.bind(0, pagesize)
////				.bind(1, offset).fetch().all());
//				
////				.bind(0, "ID")
////				.bind(1, "NAME")
////				.bind(2, "MOBNO")
////				.fetch().all();//		return (Flux<Person>) dbClient
//                return (Flux<Person>) dbClient.sql("SELECT * FROM Person where LIMIT {:pagesize} OFFSET {:offset}")
//                .fetch();
////                .map(MAPPING_FUNCTION)
////                .all();
////		return Flux.just(dbClient.sql(findPerson).fetch());
//	}
//	
//}
