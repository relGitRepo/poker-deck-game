package com.poker.deck.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.poker.deck.exception.GameNotFoundException;
import com.poker.deck.model.Card;
import com.poker.deck.model.Game;
import com.poker.deck.type.Suit;

@Repository
public interface IGameRepository {

	String createGame();

	Game findGameById(String gameId);

	void deleteGame(String gameId) throws GameNotFoundException;

	Game addDeckToGame(String gameId, String deckId) throws GameNotFoundException;

	void shuffle(String gameId) throws GameNotFoundException;

	Map<Suit, Long> countUndealtCardPerSuit(String gameId) throws GameNotFoundException;

	Map<Card, Integer> getSortedLeftCards(String gameId) throws GameNotFoundException;

}
