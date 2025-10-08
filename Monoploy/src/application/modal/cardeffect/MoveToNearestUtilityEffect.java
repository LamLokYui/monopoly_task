package application.modal.cardeffect;

import application.modal.board.Board;
import application.modal.board.squares.OwnableSquare;
import application.modal.board.squares.UtilitySquare;
import application.modal.player.Player;
import application.modal.dice.*;

/**
 * Concrete strategy for advancing to the nearest utility and handling special rent if owned.
 * Moves the player forward to the next utility position.
 * If owned, rolls dice and pays 10 times the roll to the owner.
 * If unowned, normal buying logic applies via game turn.
 */
public class MoveToNearestUtilityEffect implements CardEffect {

    @Override
    public void execute(Player player) {
        int current = player.getPosition();
        Board board = Board.getInstance();
        int newPos = (current + 1) % 40;
        for (int i = 0; i < 39; i++) { 
            if (board.getSquare(newPos) instanceof UtilitySquare) {
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
                Dice dice = new Dice();
                int roll = dice.roll();
                int rent = 10 * roll;
                player.addMoney(-rent);
                owner.addMoney(rent);
            }
        }
    }
}