package com.poker.deck.repository;

import java.util.Optional;

import com.poker.deck.model.Deck;

public interface IDeckRepository {
	
	Deck createDeck();

	Optional<Deck> findDeckById(String deckId);
}
