package br.com.manualdaprogramacao.helpdesk.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateTicketInteractionDto {

    private String message;

    private List<AttachmentDto> attachments;

}
