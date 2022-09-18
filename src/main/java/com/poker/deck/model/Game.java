package com.poker.deck.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {
	
	private String gameId;

	private final Shoe shoe;

	private List<Player> players;
	
	public Game() {
		this.gameId = UUID.randomUUID().toString(); 
		this.shoe = new Shoe();
		this.players = new ArrayList<Player>();
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
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
