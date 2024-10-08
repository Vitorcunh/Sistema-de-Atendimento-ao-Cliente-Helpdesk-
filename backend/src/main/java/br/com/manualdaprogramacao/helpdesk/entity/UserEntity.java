package br.com.manualdaprogramacao.helpdesk.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    private String password;

    private String name;

    private String email;

    private boolean active;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "created_at")
    private Date createAt;

    @Column(name = "updated_by")
    private UUID updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;

}
