package application.modal.board.squares;

import application.modal.player.Player;

public class PropertySquares extends Squares implements OwnableSquare {
    private final PropertyGroup group;
    private final int rent;
    private final int purchasePrice;
    private Player owner;

    public PropertySquares(String name, int position, PropertyGroup group, int rent, int purchasePrice) {
        super(name, position);
        this.group = group;
        this.rent = rent;
        this.purchasePrice = purchasePrice;
        this.owner = null;
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
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public int getRent() {
        return rent;
    }

    @Override
    public int getPurchasePrice() {
        return purchasePrice;
    }
}