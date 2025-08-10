package com.curso.spring.exceptions;

public class UsuarioExistenteException extends RuntimeException {
    

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7822491956217469436L;

	public UsuarioExistenteException(String message) {
        super(message);
    }
}

