package com.rabbitmq.notificacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.rabbitmq.notificacao.dto.NotificationDto;
import com.rabbitmq.notificacao.exception.EmailNotSentException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmailServiceImp implements EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Override
	public void sendEmail(NotificationDto notificationDTO) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(notificationDTO.getEmail());
			message.setSubject(notificationDTO.getSubject());
			message.setText(notificationDTO.getMessage());
			
			javaMailSender.send(message);
			System.out.println("Email enviado com sucesso para: " + notificationDTO.getEmail());
			
		} catch (Exception e) {
			throw new EmailNotSentException("Erro ao enviar e-mail para: " + notificationDTO.getEmail(), e);
		}
	}
	
}
