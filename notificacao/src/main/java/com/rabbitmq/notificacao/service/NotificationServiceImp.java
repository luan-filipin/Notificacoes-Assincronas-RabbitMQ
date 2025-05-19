package com.rabbitmq.notificacao.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.rabbitmq.notificacao.config.RabbitMQConfig;
import com.rabbitmq.notificacao.dto.NotificationDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImp implements NotificationService {
	
	private final RabbitTemplate rabbitTemplate;

	@Override
	public void sendNotification(NotificationDto notificationDto) {
		// Enviamos a mensagem para a fila "notification.queue"
		rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, notificationDto);
		System.out.println("Mensagem enviada para a fila: " + notificationDto);
		
	}


}
