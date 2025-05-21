package com.rabbitmq.notificacao.service;

import com.rabbitmq.notificacao.dto.TokenDto;
import com.rabbitmq.notificacao.dto.UserDto;

public interface AuthService {

	//Metodo para autenticar o usuario.
	TokenDto authenticate(String username, String password);
	
	//Metodo para gerar o Token.
	String generateToken(UserDto userDto);
}
