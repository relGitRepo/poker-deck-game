package com.poker.deck.repository.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.poker.deck.helper.Util;
import com.poker.deck.model.Deck;
import com.poker.deck.repository.IDeckRepository;

@Repository
public class DeckRepositoryImpl implements IDeckRepository {

	private static Map<String, Deck> decksStore = new HashMap<String, Deck>();
	
	@Override
	public Deck createDeck() {
		Deck deck = new Deck();
		decksStore.put(deck.getDeckId(), deck);
		
		Util.shuffle(deck.getCards());
		deck.setShuffled(true);
		
		return deck;
	}

	@Override
	public Optional<Deck> findDeckById(String deckId) {
		return Optional.ofNullable(decksStore.get(deckId));
	}

	public Map<String, Deck> getDecksStore() {
		return decksStore;
	}
}
