package card;

import player.Player;

public interface Card {
	void apply (Player player);
	String getDescription();
}
