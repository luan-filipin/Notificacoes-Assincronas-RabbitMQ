package com.rabbitmq.notificacao.exception;

public class EmailNotSentException extends RuntimeException {
	
	public EmailNotSentException(String message, Throwable cause) {
		super(message, cause);
	}

}
