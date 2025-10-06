package application.modal.decks;

import application.modal.decks.cards.Card;

public interface Deck {
	Card draw();
	void shuffle();
}
