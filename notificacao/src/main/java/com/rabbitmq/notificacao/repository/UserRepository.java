package com.rabbitmq.notificacao.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rabbitmq.notificacao.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	boolean existsByEmail(String email); //Método para verificar se existe o email no banco de dados.
	Optional<User> findByEmail(String email); //Método para encontrar o usuário pelo email.

}
