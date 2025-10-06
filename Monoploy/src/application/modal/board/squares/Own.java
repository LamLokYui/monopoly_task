package application.modal.board.squares;

import application.modal.player.Player;

public interface Own {
	int getRent() throws Exception;
	int getPurchasePrice();
	boolean getOwned();
	Player getOwner();
	void setOwner(Player player);
}
