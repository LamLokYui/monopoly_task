package application.modal.decks.cards;

import application.modal.player.Player;

public interface Card {
	void apply (Player player);
	String getDescription();
}
