package com.renato.biblioteca.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

	@Autowired
	SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
				.authorizeHttpRequests(authorize -> authorize
//						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
//						.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
//						.requestMatchers("/h2-console/**").permitAll()
//						.requestMatchers(HttpMethod.GET, "/categoria").hasRole("ADMIN")
//						.requestMatchers(HttpMethod.GET, "/categoria/*").hasRole("ADMIN")
//						.anyRequest().authenticated()
						.anyRequest().permitAll()
						)
				//a linha abaixo é necessária para que o h2 possa funcionar
				.headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin()))
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}