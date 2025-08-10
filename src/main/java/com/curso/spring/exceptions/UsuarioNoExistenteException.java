package com.curso.spring.exceptions;

public class UsuarioNoExistenteException extends RuntimeException {
    

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7822491956217469437L;

	public UsuarioNoExistenteException(String message) {
        super(message);
    }
}

