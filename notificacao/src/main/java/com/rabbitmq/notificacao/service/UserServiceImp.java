package com.rabbitmq.notificacao.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rabbitmq.notificacao.dto.UserDto;
import com.rabbitmq.notificacao.mapper.UserMapper;
import com.rabbitmq.notificacao.model.User;
import com.rabbitmq.notificacao.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	
	@Override
	public UserDto createdUser(UserDto userDto) {
		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new RuntimeException("Usuário com esse e-mail já está cadastrado.");
		}
		
		User user = userMapper.toEntity(userDto);
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setVerified(false);
		
		return userMapper.toDto(userRepository.save(user));
	}
}
