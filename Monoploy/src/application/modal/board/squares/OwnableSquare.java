package application.modal.board.squares;

import application.modal.player.Player;

public interface OwnableSquare {
    boolean getOwned();
    Player getOwner();
    void setOwner(Player owner);
    int getRent();
    int getPurchasePrice();
}