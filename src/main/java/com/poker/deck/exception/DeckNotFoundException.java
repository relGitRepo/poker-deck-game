package com.poker.deck.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DeckNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DeckNotFoundException() {
		super();
	}
	
	public DeckNotFoundException(String message) {
		super(message);
	}
	
	public DeckNotFoundException(Throwable cause) {
		super(cause);
	}
}
