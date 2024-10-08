package br.com.manualdaprogramacao.helpdesk.exception;

public class TooManyRequestsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TooManyRequestsException(String msg) {
		super(msg);
	}

	public TooManyRequestsException(String msg, Throwable cause) {
		super(msg, cause);
	}
}