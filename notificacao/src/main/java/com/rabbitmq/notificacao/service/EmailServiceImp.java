package com.rabbitmq.notificacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rabbitmq.notificacao.dto.NotificationDto;
import com.rabbitmq.notificacao.dto.UserDto;
import com.rabbitmq.notificacao.mapper.UserMapper;
import com.rabbitmq.notificacao.model.User;
import com.rabbitmq.notificacao.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmailServiceImp implements EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	private final UserRepository userRepository;
	
	
	@Override
	public void sendEmail(NotificationDto notificationDTO) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(notificationDTO.getEmail());
		message.setSubject(notificationDTO.getSubject());
		message.setText(notificationDTO.getMessage());
		
		javaMailSender.send(message);
		System.out.println("Email enviado com sucesso para: " + notificationDTO.getEmail());
		
	}
	
}
