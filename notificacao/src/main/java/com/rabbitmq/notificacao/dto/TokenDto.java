package com.rabbitmq.notificacao.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDto {
	
	private String token;
	private String type = "Bearer";
	private long expiresIn;
	
    public TokenDto(String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

}
