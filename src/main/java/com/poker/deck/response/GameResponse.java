package com.poker.deck.response;

public class GameResponse {

	private String gameId;

	private int totalDecksInGame;

	private int remainingCards;

	private boolean shuffled;

	private int totalCountPlayers;

	public GameResponse(String gameId, int totalDecksInGame, int remainingCards, boolean shuffled, int totalCountPlayers) {
		this.gameId = gameId;
		this.totalDecksInGame = totalDecksInGame;
		this.remainingCards = remainingCards;
		this.shuffled = shuffled;
		this.totalCountPlayers = totalCountPlayers;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public int getTotalDecksInGame() {
		return totalDecksInGame;
	}

	public void setTotalDecksInGame(int totalDecksInGame) {
		this.totalDecksInGame = totalDecksInGame;
	}

	public int getRemainingCards() {
		return remainingCards;
	}

	public void setRemainingCards(int remainingCards) {
		this.remainingCards = remainingCards;
	}

	public boolean isShuffled() {
		return shuffled;
	}

	public void setShuffled(boolean shuffled) {
		this.shuffled = shuffled;
	}

	public int getTotalCountPlayers() {
		return totalCountPlayers;
	}

	public void setTotalCountPlayers(int totalCountPlayers) {
		this.totalCountPlayers = totalCountPlayers;
	}
}
