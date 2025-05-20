package com.rabbitmq.notificacao.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.rabbitmq.notificacao.config.RabbitMQConfig;
import com.rabbitmq.notificacao.dto.NotificationDto;
import com.rabbitmq.notificacao.service.NotificationServiceImp;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class NotificationServiceTest {

	@Mock
	private RabbitTemplate rabbitTemplate; // Simulador do RabbitTemplate, que é usado para enviar mensagens para o RabbitMQ.
	
	
	@InjectMocks
	private NotificationServiceImp notificationService; // Injeção de dependência do serviço de notificações.
	
	@Test
	void sendNotificationTeste() {
		// Criação de um objeto NotificationDto para simular a notificação a ser enviada.
		NotificationDto notificationDto = new NotificationDto();
		notificationDto.setEmail("Teste@exemplo.com");
		notificationDto.setMessage("Mensagem de teste");
		notificationDto.setSubject("teste");
		
		//Chamada do metodo a ser testado.
		notificationService.sendNotification(notificationDto);
		
		//Verificação se o metodos convertAndSend foi chamado corretamente.
		verify(rabbitTemplate).convertAndSend(RabbitMQConfig.QUEUE_NAME, notificationDto);
	}
}

