package br.com.manualdaprogramacao.helpdesk.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AttachmentDto {

    private String filename;

    private String content;

}
