package com.rabbitmq.notificacao.service;

import com.rabbitmq.notificacao.dto.NotificationDto;

public interface EmailService {
	
	
	//Metodo para enviar a notificação.
	void sendEmail(NotificationDto notificationDTO);

}
