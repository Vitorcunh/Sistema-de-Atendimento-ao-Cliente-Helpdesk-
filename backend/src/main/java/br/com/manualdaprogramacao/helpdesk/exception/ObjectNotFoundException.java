package br.com.manualdaprogramacao.helpdesk.exception;


public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public ObjectNotFoundException(String error) {
        super(error);
    }


}