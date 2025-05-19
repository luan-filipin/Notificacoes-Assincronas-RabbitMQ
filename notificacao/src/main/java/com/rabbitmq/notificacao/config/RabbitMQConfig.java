package com.rabbitmq.notificacao.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String QUEUE_NAME = "notification.queue";

	// Esta anotação @Bean diz ao Spring que queremos "registrar" esse objeto
	// (Queue) como um componente da aplicação
	@Bean
	public Queue notificationQueue() {
		// Criamos uma fila com o nome "notification.queue"
		// O argumento 'true' significa que a fila é durável (vai sobreviver a reinícios
		// do RabbitMQ)
		return new Queue(QUEUE_NAME, true);
	}

	// Conversor que transforma objetos em JSON automaticamente
	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
			Jackson2JsonMessageConverter messageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}
}
