package com.poker.deck.repository;

import java.util.Optional;

import com.poker.deck.model.Deck;

public interface IDeckRepository {

	/**
	 * Create a new Deck.
	 * 
	 * @return {@link Deck} object
	 */
	Deck createDeck();

	/**
	 * Find a Deck by ID in the deckStore.
	 * 
	 * @param deckId
	 * @return {@link Optional} of {@link Deck}
	 */
	Optional<Deck> findDeckById(String deckId);
}
