package application.modal.cardeffect;

import application.modal.board.Board;
import application.modal.board.squares.OwnableSquare;
import application.modal.board.squares.UtilitySquare;
import application.modal.player.Player;
import application.modal.dice.*;

import java.util.Random;

/**
 * Concrete strategy for advancing to the nearest utility and handling special rent if owned.
 * Moves the player forward to the next utility position.
 * If owned, rolls dice and pays 10 times the roll to the owner.
 * If unowned, normal buying logic applies via game turn.
 */
public class MoveToNearestUtilityEffect implements CardEffect {
	private static final Random seed = new Random();
	private final Dice dice;

	public MoveToNearestUtilityEffect() {
		this.dice = new Dice(seed);
	}

	// Constructor for injecting a Dice instance (useful for tests)
	public MoveToNearestUtilityEffect(Dice dice) {
		this.dice = dice;
	}
	
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
        int rent = Payrent(sq, player);
        if (rent > 0) {
			player.addMoney(-rent);
			sq.getOwner().addMoney(rent);
		}
    }
    
    public int Payrent(OwnableSquare sq, Player player) {
    	int rent = 0;
    	if (sq.getOwned()) {
            Player owner = sq.getOwner();
            if (owner != player) {
                rent = getRent(rent);
            }
        }
		return rent;
    }
    
    public int getRent(int rent) {
         int roll = dice.roll();
         rent = 10 * roll;
         return rent;
    }
}