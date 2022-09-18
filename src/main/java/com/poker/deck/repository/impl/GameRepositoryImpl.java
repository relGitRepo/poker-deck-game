package com.poker.deck.repository.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poker.deck.exception.DeckNotFoundException;
import com.poker.deck.exception.GameNotFoundException;
import com.poker.deck.helper.Util;
import com.poker.deck.model.Card;
import com.poker.deck.model.Deck;
import com.poker.deck.model.Game;
import com.poker.deck.repository.IDeckRepository;
import com.poker.deck.repository.IGameRepository;
import com.poker.deck.type.Suit;

@Repository
public class GameRepositoryImpl implements IGameRepository {
	
	private static final Logger LOG = LogManager.getLogger(GameRepositoryImpl.class);
	
	@Autowired
	private IDeckRepository deckRepository;
	
	private static Map<String, Game> gameStore = new HashMap<String, Game>();

	@Override
	public String createGame() {
		Game game = new Game();
		gameStore.put(game.getGameId(), game);
		LOG.info("Starting new game ID#" + game.getGameId());
		return game.getGameId();
	}

	@Override
	public Game findGameById(String gameId) {
		return Optional.ofNullable(gameStore.get(gameId))
				.orElseThrow(() -> new GameNotFoundException("Game ID#" + gameId + " not found."));
	}

	@Override
	public void deleteGame(String gameId) throws GameNotFoundException {
		Game game = findGameById(gameId);
		gameStore.remove(game.getGameId());
		LOG.info("Game Over ID#" + game.getGameId());
	}

	@Override
	public Game addDeckToGame(String gameId, String deckId) throws GameNotFoundException, DeckNotFoundException {
		Game game = findGameById(gameId);
		Deck deck = deckRepository.findDeckById(deckId).orElseThrow(() -> new DeckNotFoundException("Deck#" + deckId + " not found."));
		
		if (deck.getGameId() != null) {
			throw new IllegalArgumentException("Deck#" + deckId + "is being used in the game.");
		}
		
		game.getShoe().addDeckToShoe(deck.getCards());
		deck.setGameId(gameId);
		
		return game;
	}

	@Override
	public void shuffle(String gameId) throws GameNotFoundException {
		Game game = findGameById(gameId);
	
		if (!game.getShoe().getCards().isEmpty()) {
			Util.shuffle(game.getShoe().getCards());
			game.getShoe().setShuffled(true);
		}
	}
	
	@Override
	public Map<Suit, Long> countUndealtCardPerSuit(String gameId) throws GameNotFoundException {
		Game game = findGameById(gameId);
		return game.getShoe().getCards().stream().collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()));
	}

	@Override
	public Map<Card, Integer> getSortedLeftCards(String gameId) throws GameNotFoundException {
		Game game = findGameById(gameId);
		
		Map<Card, Integer> cardMap = new HashMap<Card, Integer>();
		for (Card card : game.getShoe().getCards()) {
			cardMap.put(card, cardMap.getOrDefault(card, 0) + 1);
		}
		
		TreeMap<Card, Integer> sortedCardMap = new TreeMap<Card, Integer>((c1, c2) -> c2.compareTo(c1));
		sortedCardMap.putAll(cardMap);
		
		return sortedCardMap;
	}

	public Map<String, Game> getGameStore() {
		return gameStore;
	}
}
