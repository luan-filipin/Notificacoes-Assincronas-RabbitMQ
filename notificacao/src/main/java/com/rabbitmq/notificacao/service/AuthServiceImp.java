package com.rabbitmq.notificacao.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rabbitmq.notificacao.dto.TokenDto;
import com.rabbitmq.notificacao.dto.UserDto;
import com.rabbitmq.notificacao.mapper.UserMapper;
import com.rabbitmq.notificacao.model.User;
import com.rabbitmq.notificacao.repository.UserRepository;

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
	
	public AuthServiceImp(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userMapper = userMapper;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	

	@Override
	public TokenDto authenticate(String email, String password) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		UserDto userDto = userMapper.toDto(user);
		if(!passwordEncoder.matches(password, userDto.getPassword())) {
			throw new RuntimeException("Credenciais invalidas");
		}
		
		String token = generateToken(userDto);
		
		return new TokenDto(token, jwtExpiration/1000);
	}

    @Override
    public String generateToken(UserDto userDto) {
        return Jwts.builder()
                .setSubject(userDto.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
