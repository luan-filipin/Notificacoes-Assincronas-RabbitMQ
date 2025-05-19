package com.rabbitmq.notificacao.service;

import com.rabbitmq.notificacao.dto.NotificationDto;

public interface EmailService {
	
	void sendEmail(NotificationDto notificationDTO);

}
