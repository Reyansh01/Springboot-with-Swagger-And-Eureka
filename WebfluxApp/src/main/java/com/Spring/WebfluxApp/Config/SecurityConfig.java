package com.Spring.WebfluxApp.Config;

import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;



@EnableWebFluxSecurity //For application based Security
@EnableReactiveMethodSecurity  //For method based Security
public class SecurityConfig {
	
	@Bean
	public SecurityWebFilterChain securityChain(ServerHttpSecurity http) {
		
		return http.csrf().disable()
				.authorizeExchange()
//					.pathMatchers(HttpMethod.POST, "/savePerson/**").hasRole("ADMIN")
					.pathMatchers("/swawgger-ui.html",
							"/swagger-ui/**",
							"v3/api-docs/**",
							"/webjars/**",
							"/swagger-doc/**")
				.permitAll()
				.anyExchange()
//				.permitAll().and()
				.authenticated()
				.and()
					.formLogin()
				.and()
					.httpBasic()
				.and()
					.build();
	}
	
	@Bean
	public MapReactiveUserDetailsService mapUser() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetails user = User.withUsername("Person")
				.password(passwordEncoder.encode("Testing"))
				.roles("USER")
				.build();
		
		UserDetails admin = User.withUsername("Reyansh")
				.password(passwordEncoder.encode("Admin"))
				.roles("USER","ADMIN")
				.build();
		
		return new MapReactiveUserDetailsService(user, admin);
	}
	
//	@Bean
//	public ReactiveAuthenticationManager authManager(PersonService personService) {
//		return new UserDetailsRepositoryReactiveAuthenticationManager((ReactiveUserDetailsService) personService);
//	}

}
