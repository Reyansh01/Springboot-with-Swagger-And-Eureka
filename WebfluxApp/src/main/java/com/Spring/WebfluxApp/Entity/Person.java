package com.Spring.WebfluxApp.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Person {
	
	@Id
	private long id;
	private String name;
	private String mobNo;
	
	public Person() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	
	public Person(long id, String name, String mobNo) {
		super();
		this.id = id;
		this.name = name;
		this.mobNo = mobNo;
	}

	
	public static <T> PersonBuilder<T> builder() {
 		return new PersonBuilder<T>();
 	}
 	
 	public static class PersonBuilder<T> {
 		private Long id;
 		private String name;
 		private String mobNo;
 		
 		private PersonBuilder() {}
 		
 		public PersonBuilder<T> id(Long id) {
 			this.id = id;
 			return this;
 		}
 		
 		public PersonBuilder<T> name(String name) {
 			this.name = name;
 			return this;
 		}
 		
 		public PersonBuilder<T> mobNo(String mobNo) {
 			this.mobNo = mobNo;
 			return this;
 		}
 		
// 		@java.lang.Override public String toString() {
// 			return "ExampleBuilder(foo = " + foo + ", bar = " + mobNo + ")";
// 		}
 		
 		public Person build() {
 			return new Person(id, name, mobNo);
 		}
 	}
	
}
