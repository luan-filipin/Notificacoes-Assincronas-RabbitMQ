package com.rabbitmq.notificacao.service;


import com.rabbitmq.notificacao.dto.TokenDto;


public interface AuthService {

	//Metodo para autenticar o usuario.
	TokenDto authenticate(String username, String password);
}
