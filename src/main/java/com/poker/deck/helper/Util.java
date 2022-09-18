package com.poker.deck.helper;

import java.util.Random;
import java.util.Stack;

import com.poker.deck.model.Card;

public class Util {
	
	public static void shuffle(Stack<Card> cards) {
		Random random = new Random();
		
		for(int shuffleTimes = 0; shuffleTimes < 1000; shuffleTimes++) {
			for (int i = cards.size() - 1; i > 0 ; i--) {
				int randomCardIndex = random.nextInt(i + 1);

				// swap the cards
				Card temp = cards.get(i);
				cards.set(i, cards.get(randomCardIndex));
				cards.set(randomCardIndex, temp);
			}
		}
	}
}
