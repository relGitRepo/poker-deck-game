package com.poker.deck.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Game {
	
	private String gameId;

	private final Shoe shoe;

	private Set<Player> players;
	
	public Game() {
		this.gameId = UUID.randomUUID().toString(); 
		this.shoe = new Shoe();
		this.players = new HashSet<Player>();
	}

	public String getGameId() {
		return gameId;
	}
	
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public Shoe getShoe() {
		return shoe;
	}
	
	public Set<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
	
	public void checkEmptyShoe() {
		if (this.getShoe().getCards() == null || this.getShoe().getCards().isEmpty()) {
			throw new IllegalStateException("Game Deck empty. No card is dealt.");
		}
	}
}
