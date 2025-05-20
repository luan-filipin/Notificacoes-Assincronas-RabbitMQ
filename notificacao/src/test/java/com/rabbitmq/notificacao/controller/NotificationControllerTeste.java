package com.rabbitmq.notificacao.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.notificacao.dto.NotificationDto;
import com.rabbitmq.notificacao.service.NotificationService;

@ExtendWith(MockitoExtension.class)
public class NotificationControllerTeste {
	
	private MockMvc mockMvc;
	@Mock
	private NotificationService notificationService;
	@InjectMocks
	private NotificationController notificationController;
	private ObjectMapper objectMapper;
	
	
	@BeforeEach
	void setUp() {
	    mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
	    objectMapper = new ObjectMapper();

	    // Configuração do mock
	    doNothing().when(notificationService).sendNotification(any(NotificationDto.class));
	}


	@Test
	void testSendNotification() throws Exception {
	    NotificationDto notificationDto = new NotificationDto();
	    notificationDto.setMessage("Mensagem de teste");
	    notificationDto.setEmail("teste@exemplo.com");
	    notificationDto.setSubject("Assunto do email Teste");

	    String notificationJson = objectMapper.writeValueAsString(notificationDto);

	    mockMvc.perform(post("/api/v1/notifications/message")
	            .contentType("application/json")
	            .content(notificationJson))
	            .andExpect(status().isOk())
	            .andExpect(content().string("Solicitação de notificação enviada com sucesso!"));

	    verify(notificationService, times(1)).sendNotification(any(NotificationDto.class));
	}


	

}
