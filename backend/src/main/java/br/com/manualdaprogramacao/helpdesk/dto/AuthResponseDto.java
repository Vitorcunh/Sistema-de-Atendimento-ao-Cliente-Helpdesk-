package br.com.manualdaprogramacao.helpdesk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    String username;
    String accessToken;
    Long expiresIn;

}
