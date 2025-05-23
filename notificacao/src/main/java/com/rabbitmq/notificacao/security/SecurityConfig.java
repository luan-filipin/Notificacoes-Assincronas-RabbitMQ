package com.rabbitmq.notificacao.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rabbitmq.notificacao.service.JwtService;

import lombok.RequiredArgsConstructor;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(
	                "/api/v1/user/created", // Endpoint para criar usuários.
	                "/api/v1/user/token", // Endpoint para solicitar token.
	                "/v3/api-docs/**", // Documentação da API.
	                "/v3/api-docs", // Documentação da API.
	                "/swagger-ui/**", // Interface do Swagger.
	                "/swagger-ui.html", // Interface do Swagger.
	                "/swagger-resources/**", // Recursos do Swagger.
	                "/webjars/**" // Recursos do Swagger.
	            ).permitAll()
	            .anyRequest().authenticated()
	        ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}


	@Bean
	public JwtAuthFilter jwtAuthFilter(JwtService jwtService) {
		return new JwtAuthFilter(jwtService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
