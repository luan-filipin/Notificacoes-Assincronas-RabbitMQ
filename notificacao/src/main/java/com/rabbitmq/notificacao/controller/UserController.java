package com.rabbitmq.notificacao.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.notificacao.dto.TokenDto;
import com.rabbitmq.notificacao.dto.UserDto;
import com.rabbitmq.notificacao.service.AuthService;
import com.rabbitmq.notificacao.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	private final AuthService authService;

	//Metodo para criar um usuario.
	@PostMapping("/created")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		userService.createdUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
	}

	//Metodo para solicitar o Token.
	@PostMapping("/token")
	public ResponseEntity<TokenDto> getToken(@RequestBody UserDto userDto){
		try {
			// Chama o serviço de autenticação para validar as credenciais do usuário
			TokenDto tokenDto = authService.authenticate(userDto.getEmail(), userDto.getPassword());
			// Retorna o token gerado com status 200 OK
			return ResponseEntity.ok(tokenDto);
		} catch (Exception e) {
			// Retorna 401 Unauthorized se as credenciais forem inválidas
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

}
