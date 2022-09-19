package com.poker.deck.model;

import com.poker.deck.type.FaceValue;
import com.poker.deck.type.Suit;

public class Card implements Comparable<Card> {

	/** The card value */
	private FaceValue faceValue;

	/** The card suit */
	private Suit suit;

	public Card(final FaceValue faceValue, final Suit suit) {
		this.faceValue = faceValue;
		this.suit = suit;
	}

	public FaceValue getFaceValue() {
		return faceValue;
	}

	public Suit getSuit() {
		return suit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((faceValue == null) ? 0 : faceValue.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (faceValue != other.faceValue)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	/**
	 * Compares two cards numerically by their {@code Suit} then {@code FaceValue}.
	 */
	@Override
	public int compareTo(Card otherCard) {
		int res = Integer.compare(this.getSuit().getCode(), otherCard.getSuit().getCode());
		return res != 0 ? res : Integer.compare(this.getFaceValue().getValue(), otherCard.getFaceValue().getValue());
	}

	/**
	 * Returns a string representation of the card's faceValue.
	 * 
	 * @return "Ace", "2",..., "10", "Jack", "Queen" or "King".
	 */
	public String getFaceValueAsString() {
		if (this.faceValue.getValue() >= 2 && this.faceValue.getValue() <= 10) {
			return String.valueOf(this.faceValue.getValue());
		}
		return this.faceValue.name();
	}

	/**
	 * Returns a string representation of this card.
	 * 
	 * @return samples: "Card[Ace-Hearts]", "Card[SEVEN-Diamonds]".
	 */
	@Override
	public String toString() {
		return String.format("Card[%s-%s]", this.faceValue.name(), this.suit.name());
	}
}
