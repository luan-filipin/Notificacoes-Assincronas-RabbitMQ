package com.rabbitmq.notificacao.service;

import com.rabbitmq.notificacao.dto.UserDto;

public interface UserService {
	
	//Metodo para criar o user
	UserDto createdUser(UserDto userDto);

}
