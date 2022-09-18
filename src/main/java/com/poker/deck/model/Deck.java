package com.poker.deck.model;

import java.util.Stack;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.poker.deck.type.FaceValue;
import com.poker.deck.type.Suit;

@JsonFilter("DeckFilter")
public class Deck {

	private String deckId;
	private final Stack<Card> cards;
	private boolean shuffled;
	private String gameId;

	public Deck() {
		this.deckId = UUID.randomUUID().toString();
		this.setShuffled(false);
		this.gameId = null;
		this.cards = newDeck();
	}

	public String getDeckId() {
		return deckId;
	}

	public void setDeckId(String deckId) {
		this.deckId = deckId;
	}

	public Stack<Card> getCards() {
		return cards;
	}

	public int cardsLeft() {
        return this.cards.size();
    }
	
	public boolean isShuffled() {
		return shuffled;
	}

	public void setShuffled(boolean shuffled) {
		this.shuffled = shuffled;
	}
	
	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	private Stack<Card> newDeck() {
		Stack<Card> cards = new Stack<Card>();
		
		for (Suit suit : Suit.values()) {
			for (FaceValue faceValue : FaceValue.values()) {
				cards.push(new Card(faceValue, suit));
			}
		}

		return cards;
	}
}
