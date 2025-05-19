package com.rabbitmq.notificacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.rabbitmq.notificacao.dto.NotificationDto;

public class EmailServiceImp implements EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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
