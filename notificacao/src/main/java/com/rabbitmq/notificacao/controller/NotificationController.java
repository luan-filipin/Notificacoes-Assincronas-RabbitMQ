package com.rabbitmq.notificacao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.notificacao.dto.NotificationDto;
import com.rabbitmq.notificacao.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
	
	private final NotificationService notificationService;
	
	
	@PostMapping("/message")
	public ResponseEntity<String> sendNotification(@RequestBody NotificationDto notificationDto) {
		notificationService.sendNotification(notificationDto);
		return ResponseEntity.ok("Solicitação de notificação enviada com sucesso!");
	}
	

}
