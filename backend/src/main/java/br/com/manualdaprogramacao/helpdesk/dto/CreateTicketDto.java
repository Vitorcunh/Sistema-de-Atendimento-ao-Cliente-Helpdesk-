package br.com.manualdaprogramacao.helpdesk.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateTicketDto {

    private String subject;

    private String description;

    private List<AttachmentDto> attachments;

}
