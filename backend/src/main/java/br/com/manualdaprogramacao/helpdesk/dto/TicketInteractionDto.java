package br.com.manualdaprogramacao.helpdesk.dto;

import br.com.manualdaprogramacao.helpdesk.enums.TicketStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class TicketInteractionDto {

    private UUID id;

    private String message;

    private TicketStatus status;

    private List<AttachmentDto> attachments;

    private UserDto sentByUser;

    private Date createAt;




}
