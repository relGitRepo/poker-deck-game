package com.poker.deck.type;

public enum Suit {
	CLUB(1),
	DIAMOND(2),
	HEART(3),
	SPADE(4);
	
	private final int code;

	private Suit(final int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
}
