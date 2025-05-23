package com.rabbitmq.notificacao.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.rabbitmq.notificacao.dto.NotificationDto;
import com.rabbitmq.notificacao.exception.EmailNotSentException;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

	@Mock
	private JavaMailSender javaMailSender;

	@InjectMocks
	private EmailServiceImp emailService;

	@Test
	void sendEmailTest() {

		// Arrrange.
		NotificationDto notificationDTO = new NotificationDto("teste@gmail.com", "Assunto", "Mensagem");

		// Action.
		emailService.sendEmail(notificationDTO);

		// Assert.
		ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
		verify(javaMailSender, times(1)).send(messageCaptor.capture());

		SimpleMailMessage setMessage = messageCaptor.getValue();
		assertThat(setMessage.getTo()[0]).isEqualTo("teste@gmail.com");
		assertThat(setMessage.getSubject()).isEqualTo("Assunto");
		assertThat(setMessage.getText()).isEqualTo("Mensagem");

	}
	
	//Criamos um teste para verificar quando a uma falha no envio do email.
	@Test
	void whenSendEmailFails() {
		
		// Arrrange.
		NotificationDto notificationDTO = new NotificationDto("teste@gmail.com", "Assunto", "Mensagem");
		
		//Monta uma Exception para o teste.
		doThrow(new MailSendException("Falha no envio de e-mail"))
		.when(javaMailSender)
		.send(any(SimpleMailMessage.class));
		
		//Act & Assert.
		EmailNotSentException exception = assertThrows(
				EmailNotSentException.class, 
				() -> emailService.sendEmail(notificationDTO)
		);
		
		assertThat(exception.getMessage())
		.isEqualTo("Erro ao enviar e-mail para: teste@gmail.com");
	}
}
