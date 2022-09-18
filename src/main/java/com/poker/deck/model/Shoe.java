package com.poker.deck.model;

import java.util.Stack;

public class Shoe {

	private Stack<Card> cards;
	private int totalDecksInGame;
	private boolean shuffled;

	public Shoe() {
		this.cards = new Stack<Card>();
		this.shuffled = false;
	}

	public Stack<Card> getCards() {
		return cards;
	}

	public void setCards(Stack<Card> cards) {
		this.cards = cards;
	}
	
	public boolean isShuffled() {
		return shuffled;
	}

	public void setShuffled(boolean shuffled) {
		this.shuffled = shuffled;
	}
	
	public int getTotalDecksInGame() {
		return totalDecksInGame;
	}

	public void setTotalDecksInGame(int totalDecksInGame) {
		this.totalDecksInGame = totalDecksInGame;
	}

	public void addDeckToShoe(Stack<Card> newCards) {
		this.cards.addAll(newCards);
		this.totalDecksInGame++;
	}
}
