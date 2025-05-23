package com.rabbitmq.notificacao.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.rabbitmq.notificacao.model.User;

@DataMongoTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		userRepository.deleteAll();
	}

	@Test
	void shouldCheckIfEmailExists() {

		User user = new User();
		user.setEmail("joao@gmail.com");
		user.setPassword("123456");
		user.setVerified(false);
		userRepository.save(user);

		// Action.
		boolean exist = userRepository.existsByEmail("joao@gmail.com");

		// Assert.
		assertThat(exist).isTrue();
	}

	@Test
	void shouldFindUserByEmail() {

		User user = new User();
		user.setEmail("joao@gmail.com");
		user.setPassword("123456");
		user.setVerified(false);
		userRepository.save(user);

		// Action.
		Optional<User> result = userRepository.findByEmail("joao@gmail.com");

		// Assert.
		assertThat(result).isPresent();
		assertThat(result.get().getEmail()).isEqualTo("joao@gmail.com");
	}

	@Test
	void shouldReturnFalseIfEmailDoesNotExist() {

		// Action.
		boolean exists = userRepository.existsByEmail("naoexiste@gmail.com");

		// Assert.
		assertThat(exists).isFalse();
	}
}
