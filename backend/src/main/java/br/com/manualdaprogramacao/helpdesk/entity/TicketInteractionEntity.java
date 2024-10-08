package br.com.manualdaprogramacao.helpdesk.entity;


import br.com.manualdaprogramacao.helpdesk.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "ticket_interactions")
public class TicketInteractionEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticket;

    @ManyToOne
    @JoinColumn(name = "sent_by_user_id")
    private UserEntity sentByUser;

    private String message;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;

    @Column(name = "created_at")
    private Date createAt;

    @Column(name = "updated_by")
    private UUID updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;

}
