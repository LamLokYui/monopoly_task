package application.modal.cardeffect;

import application.modal.board.Board;
import application.modal.board.squares.OwnableSquare;
import application.modal.board.squares.RailroadSquare;
import application.modal.player.Player;

/**
 * Concrete strategy for advancing to the nearest railroad and handling special rent if owned.
 * Moves the player forward to the next railroad position.
 * If owned, pays double the normal rent to the owner.
 * If unowned, normal buying logic applies via game turn.
 */
public class MoveToNearestRailroadEffect implements CardEffect {

    @Override
    public void execute(Player player) {
        int current = player.getPosition();
        Board board = Board.getInstance();
        int newPos = (current + 1) % 40;
        for (int i = 0; i < 39; i++) { 
            if (board.getSquare(newPos) instanceof RailroadSquare) {
                break;
            }
            newPos = (newPos + 1) % 40;
        }
        int steps = (newPos - current + 40) % 40;
        player.move(steps); 

        OwnableSquare sq = (OwnableSquare) board.getSquare(newPos);
        if (sq.getOwned()) {
            Player owner = sq.getOwner();
            if (owner != player) {
                int rent = sq.getRent() * 2;
                player.addMoney(-rent);
                owner.addMoney(rent);
            }
        }
       
    }
}