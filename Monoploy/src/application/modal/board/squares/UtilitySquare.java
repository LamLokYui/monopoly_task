package application.modal.board.squares;

import application.modal.dice.Dice;
import application.modal.player.Player;


public class UtilitySquare extends Squares implements OwnableSquare{
	private final int purchasePrice;
	private Player owner;
	
	public UtilitySquare(String name, int position) {
		super(name, position);
		this.purchasePrice = 150;
		this.owner = null;
	}
	
	@Override
	public int getPurchasePrice() {
		return purchasePrice;
	}
	
	
	@Override 
	public boolean getOwned() {
		return owner != null;
	}
	
	@Override
	public Player getOwner() {
		return owner;
	}
	
	@Override
	public void setOwner(Player player) {
		owner = player;
	}
	
	@Override
	public int getRent() {
		return 0;
	}
	
	public int getRent(Dice dice) {
		dice.roll();
		if (owner.getNumOfOwnedUtilities() > 1) {
			return 10 * dice.getTotal();
		}
		return 4* dice.getTotal();
	}
	
}
