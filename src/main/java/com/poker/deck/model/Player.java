package com.poker.deck.model;

import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player>{

	/** The player ID */
	private String playerId;

	/** The list of cards holding by the player */
	private List<Card> hand = new ArrayList<Card>();

	/** The player score representing the total added value of all his card's face value */
	private int score = 0;

	public Player(String playerId) {
		this.playerId = playerId;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public int getScore() {
		this.score = hand.stream().mapToInt(card -> card.getFaceValue().getValue()).sum();
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public int compareTo(Player otherPlayer) {
		return Integer.compare(this.getScore(), otherPlayer.getScore());
	}
}
