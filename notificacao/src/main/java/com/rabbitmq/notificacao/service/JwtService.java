package com.rabbitmq.notificacao.service;

import org.springframework.security.core.Authentication;

public interface JwtService {
	
	//Metodo para gerar o Token.
	String generateToken(String email);
	
	//Metodo para validar o Token.
	boolean isValidToken(String token);
	
	//Metodo para obter o usuario do Token.
	Authentication getAuthentication(String token);
	
	//Metodo para obter o usuario do Token.
	String extractUsername(String token);
	
	long getExpirationInSeconds();
}
