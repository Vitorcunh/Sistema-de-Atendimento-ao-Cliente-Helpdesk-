package br.com.manualdaprogramacao.helpdesk.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper=false)
@Getter
public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1L;
    private List<FieldMessage> errors = new ArrayList<>();
    public ValidationError(Integer status, String error, Long timeStamp) {
        super(status, error, error, timeStamp);
    }
    public void addError(String fieldName, String errorCode, String msg) {
        errors.add(new FieldMessage(fieldName, errorCode, msg));
    }
}