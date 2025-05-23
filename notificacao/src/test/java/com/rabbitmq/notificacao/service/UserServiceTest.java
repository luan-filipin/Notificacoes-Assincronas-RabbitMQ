package com.rabbitmq.notificacao.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rabbitmq.notificacao.dto.UserDto;
import com.rabbitmq.notificacao.mapper.UserMapper;
import com.rabbitmq.notificacao.model.User;
import com.rabbitmq.notificacao.repository.UserRepository;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository; 
	@Mock
	private UserMapper userMapper; 
	@Mock 
	private PasswordEncoder passwordEncoder; 
	@InjectMocks
	private UserServiceImp userService;
	
	@Test
	void shouldCreateUserSuccessfully() {
		
		UserDto userDto = new UserDto("joao@gmail.com", "12345678");
		
		User user = new User();
		user.setEmail("joao@gmail.com");
		
		User savedUser = new User();
		savedUser.setId("1L");
		savedUser.setEmail("joao@gmail.com");
		
		UserDto expectedUserDto = new UserDto();
		expectedUserDto.setEmail("joao@gmail.com");
		
		//Configurando o comportamento dos mocks.
		when(userRepository.existsByEmail("joao@gmail.com")).thenReturn(false);
		when(userMapper.toEntity(userDto)).thenReturn(user);
		when(passwordEncoder.encode("12345678")).thenReturn("encoded-password");
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.toDto(savedUser)).thenReturn(expectedUserDto);
        
        //Action.
        UserDto result = userService.createdUser(userDto);
        
        //Assert.
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("joao@gmail.com");
        
        //Verifica se os metodos foram chamados corretamente.
        verify(userRepository).existsByEmail("joao@gmail.com");
        verify(passwordEncoder).encode("12345678");
        verify(userRepository).save(user);
        	
		
	}

}
