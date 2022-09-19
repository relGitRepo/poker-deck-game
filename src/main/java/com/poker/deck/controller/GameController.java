package com.poker.deck.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.poker.deck.model.Card;
import com.poker.deck.model.Deck;
import com.poker.deck.model.Game;
import com.poker.deck.model.Player;
import com.poker.deck.repository.IDeckRepository;
import com.poker.deck.repository.IGameRepository;
import com.poker.deck.response.GameResponse;
import com.poker.deck.type.Suit;

@RestController
public class GameController {
	
	@Autowired
	private IGameRepository gameRepository;

	@Autowired
	private IDeckRepository deckRepository;

	@GetMapping(value = "/game/new")
	public String createGame() {
		return gameRepository.createGame();
	}

	@GetMapping(value = "/game/{id}")
	public ResponseEntity<GameResponse> getGameInfo(@PathVariable("id") String gameId) {
		Game game = gameRepository.findGameById(gameId);

		GameResponse response = new GameResponse(game.getGameId(), game.getShoe().getTotalDecksInGame(),
				game.getShoe().getCards().size(), game.getShoe().isShuffled(), game.getPlayers().size());

		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/game/{id}")
	public void deleteGame(@PathVariable("id") String gameId) {
		gameRepository.deleteGame(gameId);
	}

	@GetMapping(value = "/game/deck/new")
	public MappingJacksonValue createDeck() {
		Deck deck = deckRepository.createDeck();

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("deckId", "shuffled");
		FilterProvider filters = new SimpleFilterProvider().addFilter("DeckFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(deck);
		mapping.setFilters(filters);

		return mapping;
	}

	@PostMapping(value = "/game/{id}/deck/add/{deck_id}")
	public ResponseEntity<GameResponse> addDeckToGame(@PathVariable("id") String gameId, @PathVariable("deck_id") String deckId) {
		gameRepository.addDeckToGame(gameId, deckId);

		return this.getGameInfo(gameId);
	}

	@PostMapping(value = "/game/{id}/shuffle")
	public void shuffle(@PathVariable("id") String gameId) {
		gameRepository.shuffle(gameId);
	}

	@GetMapping(value = "/game/{id}/undealtcardpersuit")
	public Map<Suit, Long> getUndealtCardPerSuit(@PathVariable("id") String gameId) {
		return gameRepository.countUndealtCardPerSuit(gameId);
	}

	@GetMapping(value = "/game/{id}/sortedleftcards")
	public Map<Card, Integer> getSortedLeftCards(@PathVariable("id") String gameId) {
		return gameRepository.getSortedLeftCards(gameId);
	}
	
	
	@PostMapping(value = "/game/{id}/player/add/{player_id}")
	public ResponseEntity<GameResponse> addPlayerToGame(@PathVariable("id") String gameId, @PathVariable("player_id") String playerId) {
		gameRepository.addPlayerToGame(gameId, playerId);
		
		return this.getGameInfo(gameId);
	}

	@DeleteMapping(value = "/game/{id}/player/remove/{player_id}")
	public ResponseEntity<GameResponse> removePlayerFromGame(@PathVariable("id") String gameId, @PathVariable("player_id") String playerId) {
		gameRepository.removePlayerFromGame(gameId, playerId);
		
		return this.getGameInfo(gameId);
	}
	
	@GetMapping(value = "/game/{id}/player/{player_id}/deal")
	void dealCards(@PathVariable("id") String gameId, @PathVariable("player_id") String playerId, @RequestParam(defaultValue = "1") int nbrCards) {
		gameRepository.dealCardsToPlayer(gameId, playerId, nbrCards);
	}
	
	@GetMapping(value = "/game/{id}/player/{player_id}/cards")
	public List<Card> getPlayerCardList(@PathVariable("id") String gameId, @PathVariable("player_id") String playerId) {
		return gameRepository.getPlayerCardList(gameId, playerId);
	}

	@GetMapping(value = "/game/{id}/players")
	List<Player> getPlayers(@PathVariable("id") String gameId) {
		return gameRepository.getPlayerListSortedbyScore(gameId);
	}
}
