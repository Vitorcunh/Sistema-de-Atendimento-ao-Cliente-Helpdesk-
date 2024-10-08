package br.com.manualdaprogramacao.helpdesk.service;

import br.com.manualdaprogramacao.helpdesk.domain.Attachment;
import br.com.manualdaprogramacao.helpdesk.domain.Ticket;
import br.com.manualdaprogramacao.helpdesk.domain.TicketInteraction;
import br.com.manualdaprogramacao.helpdesk.domain.User;
import br.com.manualdaprogramacao.helpdesk.entity.TicketAttachmentEntity;
import br.com.manualdaprogramacao.helpdesk.entity.TicketEntity;
import br.com.manualdaprogramacao.helpdesk.entity.TicketInteractionEntity;
import br.com.manualdaprogramacao.helpdesk.entity.UserEntity;
import br.com.manualdaprogramacao.helpdesk.enums.TicketStatus;
import br.com.manualdaprogramacao.helpdesk.exception.BusinessException;
import br.com.manualdaprogramacao.helpdesk.exception.ObjectNotFoundException;
import br.com.manualdaprogramacao.helpdesk.mapper.TicketMapper;
import br.com.manualdaprogramacao.helpdesk.mapper.UserMapper;
import br.com.manualdaprogramacao.helpdesk.repository.TicketAttachmentRepository;
import br.com.manualdaprogramacao.helpdesk.repository.TicketInteractionRepository;
import br.com.manualdaprogramacao.helpdesk.repository.TicketRepository;
import br.com.manualdaprogramacao.helpdesk.repository.UserRepository;
import br.com.manualdaprogramacao.helpdesk.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final TicketInteractionRepository ticketInteractionRepository;

    private final TicketAttachmentRepository ticketAttachmentRepository;

    private final UserRepository userRepository;

    private final TicketMapper mapper;

    @Value("${helpdesk.attachments-folder}")
    private String attachmentsFolder;

    @Transactional
    public Ticket createTicket(Ticket newTicket, String username) {



        TicketEntity entity = mapper.toEntity(newTicket);
        UserEntity createdByUser = userRepository.findByUsername(username).orElse(null);
        if (createdByUser == null) {
            throw new ObjectNotFoundException("User not found with provided id");
        }
        entity.setCreatedBy(createdByUser);
        entity.setStatus(TicketStatus.OPEN);
        entity.setCreateAt(new Date());
        entity = ticketRepository.save(entity);

        if (newTicket.getAttachments() != null && !newTicket.getAttachments().isEmpty()) {
            for (Attachment attachment : newTicket.getAttachments()) {
                TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
                ticketAttachmentEntity.setTicket(entity);
                ticketAttachmentEntity.setCreatedBy(createdByUser);
                ticketAttachmentEntity.setCreateAt(new Date());
                ticketAttachmentEntity.setFilename(attachment.getFilename());
                ticketAttachmentEntity = ticketAttachmentRepository.save(ticketAttachmentEntity);
                saveFileToDisk(ticketAttachmentEntity, attachment.getContent());
            }
        }

        return mapper.toDomain(entity);
    }

    public Ticket ticketInteract(TicketInteraction domain, String username) {
        TicketEntity ticket = ticketRepository.findById(domain.getTicketId()).orElse(null);

        if (ticket == null) {
            throw new BusinessException("Ticket not found with provided id");
        }

        UserEntity user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            throw new BusinessException("User not found with provided id");
        }

        Date now = new Date();
        TicketStatus status = TicketStatus.IN_PROGRESS;
        if (ticket.getCreatedBy().getId() != user.getId()) {
            ticket.setSupportUser(user);
            status = TicketStatus.AWAITING_CUSTOMER_ANSWER;
        }

        TicketInteractionEntity entity = new TicketInteractionEntity();
        entity.setTicket(ticket);
        entity.setMessage(domain.getMessage());
        entity.setCreatedBy(user);
        entity.setSentByUser(user);
        entity.setCreateAt(now);
        entity.setStatus(status);
        entity = ticketInteractionRepository.save(entity);

        if (domain.getAttachments() != null && !domain.getAttachments().isEmpty()) {
            for (Attachment attachment : domain.getAttachments()) {
                TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
                ticketAttachmentEntity.setTicketInteraction(entity);
                ticketAttachmentEntity.setCreatedBy(user);
                ticketAttachmentEntity.setCreateAt(new Date());
                ticketAttachmentEntity.setFilename(attachment.getFilename());
                ticketAttachmentEntity = ticketAttachmentRepository.save(ticketAttachmentEntity);
                saveFileToDisk(ticketAttachmentEntity, attachment.getContent());
            }
        }

        ticket.setUpdatedAt(now);
        ticket.setUpdatedBy(user.getId());
        ticket.setStatus(status);
        ticket = ticketRepository.save(ticket);

        return mapper.toDomain(ticket);
    }

    private void saveFileToDisk(TicketAttachmentEntity entity, String content) {
        byte[] attachmentContent = null;
        try {
            attachmentContent = FileUtils.convertBase64ToByteArray(content);
            String fileName = entity.getId().toString();

            FileUtils.saveByteArrayToFile(attachmentContent, new File(attachmentsFolder + fileName));
        } catch (IOException ex) {
            throw new BusinessException("Error saving " + entity.getFilename() + " file");
        }
    }

    public List<Ticket> listAll() {
        return mapper.toDomain(ticketRepository.findAll());
    }

    public Ticket getById(UUID ticketId) {
        TicketEntity entity = ticketRepository.findById(ticketId).orElse(null);
        if (entity == null) {
            throw new ObjectNotFoundException("Ticket was not found");
        }
        return mapper.toDomain(entity);
    }

    public List<TicketInteraction> getInteractionsByTicketId(UUID ticketId) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId).orElse(null);
        if (ticketEntity == null) {
            throw new ObjectNotFoundException("Ticket was not found");
        }
        return mapper.toInteractionDomain(ticketInteractionRepository.findByTicket(ticketEntity));
    }
}
