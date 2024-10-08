package br.com.manualdaprogramacao.helpdesk.repository;

import br.com.manualdaprogramacao.helpdesk.domain.Ticket;
import br.com.manualdaprogramacao.helpdesk.entity.TicketAttachmentEntity;
import br.com.manualdaprogramacao.helpdesk.entity.TicketEntity;
import br.com.manualdaprogramacao.helpdesk.entity.TicketInteractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketInteractionRepository extends JpaRepository<TicketInteractionEntity, UUID> {


    List<TicketInteractionEntity> findByTicket(TicketEntity ticket);


}
