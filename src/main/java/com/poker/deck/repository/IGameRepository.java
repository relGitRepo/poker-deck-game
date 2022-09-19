package com.poker.deck.repository;

import java.util.List;
import java.util.Map;

import com.poker.deck.exception.DeckNotFoundException;
import com.poker.deck.exception.GameNotFoundException;
import com.poker.deck.exception.PlayerNotFoundException;
import com.poker.deck.model.Card;
import com.poker.deck.model.Game;
import com.poker.deck.model.Player;
import com.poker.deck.type.Suit;

public interface IGameRepository {

	/**
	 * Create a new game.
	 * 
	 * @return the game ID
	 */
	String createGame();

	/**
	 * Find a game by ID.
	 * 
	 * @param gameId
	 * @return {@link Game} object
	 */
	Game findGameById(String gameId);

	/**
	 * Delete a game.
	 * 
	 * @param gameId
	 * @throws GameNotFoundException
	 */
	void deleteGame(String gameId) throws GameNotFoundException;

	/**
	 * Add a deck to the game.
	 * 
	 * @param gameId
	 * @param deckId
	 * @return {@link Game} object
	 * @throws GameNotFoundException, DeckNotFoundException
	 */
	Game addDeckToGame(String gameId, String deckId) throws GameNotFoundException, DeckNotFoundException;

	/**
	 * Shuffle the game deck.
	 * 
	 * @param gameId
	 * @throws GameNotFoundException
	 */
	void shuffle(String gameId) throws GameNotFoundException;

	/**
	 * Get the count of how many cards per suit are left undealt in the game deck.
	 * 
	 * @param gameId
	 * @return {@link Map}
	 * @throws GameNotFoundException
	 */
	Map<Suit, Long> countUndealtCardPerSuit(String gameId) throws GameNotFoundException;

	/**
	 * Get the count of each card remaining in the game deck sorted by suit and face
	 * from high value to low value.
	 * 
	 * @param gameId
	 * @return {@link Map}
	 * @throws GameNotFoundException
	 */
	Map<Card, Integer> getSortedLeftCards(String gameId) throws GameNotFoundException;

	/**
	 * Add a player to the game.
	 * 
	 * @param gameId
	 * @param playerId
	 * @throws GameNotFoundException
	 */
	void addPlayerToGame(String gameId, String playerId) throws GameNotFoundException;

	/**
	 * Add a player from the game.
	 * 
	 * @param gameId
	 * @param playerId
	 * @throws GameNotFoundException, PlayerNotFoundException
	 */
	void removePlayerFromGame(String gameId, String playerId) throws GameNotFoundException, PlayerNotFoundException;

	/**
	 * Deal cards to a player in a game from the game deck.
	 * 
	 * @param gameId
	 * @param playerId
	 * @param nbrCards
	 * @throws GameNotFoundException, PlayerNotFoundException
	 */
	void dealCardsToPlayer(String gameId, String playerId, int nbrCards) throws GameNotFoundException, PlayerNotFoundException;

	/**
	 * Get the list of cards for a player.
	 * 
	 * @param gameId
	 * @param playerId
	 * @return {@link List} of {@link Card}
	 * @throws GameNotFoundException, PlayerNotFoundException
	 */
	List<Card> getPlayerCardList(String gameId, String playerId) throws GameNotFoundException, PlayerNotFoundException;

	/**
	 * Get the list of players ordered by their score in descending order.
	 * 
	 * @param gameId
	 * @return {@link List} of {@link Player}
	 * @throws GameNotFoundException
	 */
	List<Player> getPlayerListSortedbyScore(String gameId) throws GameNotFoundException;
}
