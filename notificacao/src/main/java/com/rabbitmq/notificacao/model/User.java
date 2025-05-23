package com.rabbitmq.notificacao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document(collection = "users") //Acessa a tabela users.
public class User {
	
	@Id
	private String id;
	
	private String email;
	private String password;
	private String secretKey;
	private boolean verified; //Verifica se o usuário já foi verificado ou não.

}
