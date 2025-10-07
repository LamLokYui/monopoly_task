package application.modal.board.squares;

import application.modal.player.Player;

public class RailroadSquare extends Squares implements OwnableSquare{
	private final int rent;
	private Player owner;
	private final int purchasePrice;
	
	private int calculateRent(int NumOfOwnedRails) {
		switch (NumOfOwnedRails) {
		case 2 : return rent *2;
		case 3: return rent * 3;
		case 4: return rent * 4;
		default : return rent;
		}
	}
	
	public RailroadSquare (String name, int position, int rent) {
		super(name, position);
		this.rent = rent;
		this.owner = null;
		this.purchasePrice = 200;
	}
	
	@Override
	public int getRent() {
		if (owner == null) return 0;
		
		return calculateRent(owner.getNumOfOwnedRails());
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
}
