package br.com.manualdaprogramacao.helpdesk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDto {
    String username;
    String password;

}
