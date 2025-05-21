package com.rabbitmq.notificacao.mapper;

import org.mapstruct.Mapper;

import com.rabbitmq.notificacao.dto.UserDto;
import com.rabbitmq.notificacao.model.User;

@Mapper(componentModel = "spring") // Define o modelo de componente como Spring
public interface UserMapper {
	
	//Método para converter o Dto em um User.
	UserDto toDto(User user);
	
	//Metodos para converter um user em um Dto.
	User toEntity(UserDto userDto);

}
