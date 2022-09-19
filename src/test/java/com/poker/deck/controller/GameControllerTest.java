package com.poker.deck.controller;

import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.poker.deck.PokerDeckGameApplication;
import com.poker.deck.model.Deck;
import com.poker.deck.repository.IDeckRepository;
import com.poker.deck.repository.IGameRepository;

@SpringBootTest(classes = PokerDeckGameApplication.class, webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GameControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private IGameRepository gameRepository;

	@Autowired
	private IDeckRepository deckRepository;

	private String gameId;

	private Deck deck;

	@BeforeEach
	public void setUp() {
		gameId = gameRepository.createGame();
		deck = deckRepository.createDeck();
		gameRepository.addPlayerToGame(gameId, "P1");
	}

	@Test
	public void testAddDeckToGame() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/game/" + gameId + "/deck/add/" + deck.getDeckId())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalDecksInGame").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.remainingCards").value(52))
				.andExpect(MockMvcResultMatchers.jsonPath("$.shuffled").value(false))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalCountPlayers").value(1)).andReturn();
	}

	@Test
	public void testGetUndealtCardPerSuit() throws Exception {
		gameRepository.addDeckToGame(gameId, deck.getDeckId());

		mvc.perform(MockMvcRequestBuilders.get("/game/" + gameId + "/undealtcardpersuit")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.HEART").value(13))
				.andExpect(MockMvcResultMatchers.jsonPath("$.SPADE").value(13))
				.andExpect(MockMvcResultMatchers.jsonPath("$.CLUB").value(13))
				.andExpect(MockMvcResultMatchers.jsonPath("$.DIAMOND").value(13)).andReturn();
	}

	@Test
	public void testDealCards() throws Exception {
		gameRepository.addDeckToGame(gameId, deck.getDeckId());
		gameRepository.dealCardsToPlayer(gameId, "P1", 3);

		mvc.perform(MockMvcRequestBuilders.get("/game/" + gameId).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.remainingCards").value(49))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalCountPlayers").value(1)).andReturn();
	}

	@Test
	public void testGetPlayersOrderByScore() throws Exception {
		gameRepository.addDeckToGame(gameId, deck.getDeckId());
		gameRepository.dealCardsToPlayer(gameId, "P1", 52);

		mvc.perform(MockMvcRequestBuilders.get("/game/" + gameId + "/players").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].playerId").value("P1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].hand", hasSize(52)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].score").value(364)).andReturn();
	}

	@Test
	public void testRemovePlayerFromGame() throws Exception {
		gameRepository.addDeckToGame(gameId, deck.getDeckId());

		mvc.perform(MockMvcRequestBuilders.delete("/game/" + gameId + "/player/remove/P1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalCountPlayers").value(0)).andReturn();
	}
}
