package java.model.decks;

import java.model.decks.cards.Card;

public interface Deck {
	Card draw();
	void shuffle();
}
