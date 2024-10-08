package br.com.manualdaprogramacao.helpdesk.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer statusCode;
    private String errorCode;
    private String message;
    private Long timeStamp;
}