package com.rabbitmq.notificacao.service;

import com.rabbitmq.notificacao.dto.NotificationDto;

public interface NotificationService {
	
	void sendNotification(NotificationDto notificationDto);

}
