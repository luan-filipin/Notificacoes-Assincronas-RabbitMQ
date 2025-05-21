package com.rabbitmq.notificacao.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(
	                "/api/v1/notifications/message", // Endpoint para enviar notificações.
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
	        );

	    return http.build();
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
