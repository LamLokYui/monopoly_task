package deck;

import card.Card;

public interface Deck {
	Card draw();
	void shuffle();
}
