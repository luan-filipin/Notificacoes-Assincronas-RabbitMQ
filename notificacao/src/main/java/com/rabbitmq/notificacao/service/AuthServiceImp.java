package com.rabbitmq.notificacao.service;

import java.util.ArrayList;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.rabbitmq.notificacao.dto.TokenDto;
import com.rabbitmq.notificacao.dto.UserDto;
import com.rabbitmq.notificacao.mapper.UserMapper;
import com.rabbitmq.notificacao.model.User;
import com.rabbitmq.notificacao.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class AuthServiceImp implements AuthService{
	
	private final UserMapper userMapper;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private SecretKey key;
	

	//Lê as propriedades do arquivo application.yml
	@Value("${jwt.secret}")
	private String jwtSecret;
	//Lê as propriedades do arquivo application.yml
	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	//inicializa a chave assim que o Bean é criado.
	@PostConstruct
	public void init() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	public AuthServiceImp(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userMapper = userMapper;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}
	

    @Override
    public TokenDto authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new TokenDto(token, jwtService.getExpirationInSeconds());
    }
}
