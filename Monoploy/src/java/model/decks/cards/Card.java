package java.model.decks.cards;

import java.model.player.Player;

public interface Card {
	void apply (Player player);
	String getDescription();
}
