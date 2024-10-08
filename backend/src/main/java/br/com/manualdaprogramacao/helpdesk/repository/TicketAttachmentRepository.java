package br.com.manualdaprogramacao.helpdesk.repository;

import br.com.manualdaprogramacao.helpdesk.entity.TicketAttachmentEntity;
import br.com.manualdaprogramacao.helpdesk.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketAttachmentRepository extends JpaRepository<TicketAttachmentEntity, UUID> {

}
