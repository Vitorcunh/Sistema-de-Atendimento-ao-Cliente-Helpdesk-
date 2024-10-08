package br.com.manualdaprogramacao.helpdesk.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserDto {
    UUID id;
    String username;
    String name;
    String email;
    Date createAt;

}
