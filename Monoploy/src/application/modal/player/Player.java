package application.modal.player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import application.modal.board.Board;
import application.modal.board.squares.*;

//Player info initialize
public class Player{
	private String name;
	private int pos;
	private int capital;
	private List<PropertySquares> ownedProperty;
	private List<Own> ownedSquares;
	private boolean isJail;
	private boolean isBankrupt;
	
	public Player(String name) {
		this.name = name;
		this.pos = 0;
		this.capital = 1500;
		this.ownedSquares =new ArrayList<>();
		this.ownedProperty = new ArrayList<>();
		this.isJail = false;
		this.isBankrupt = false;
	}
	
	public void move(int steps) {
		int newpos = (pos + steps) % 40;
		if (newpos < pos) {
			capital += 200;
		}
		pos = newpos;
	}
	
	public void setBankrupt(boolean bankrupt) {
		isBankrupt = bankrupt;
	}
	
	public void setJail(boolean jail) {
		isJail = jail;
	}
	
	public int changeOfCapital(int amount) {
        if ((-amount) > capital) {
            int moneyNeeded = -(capital + amount);
            autoSellProperties(moneyNeeded);
        }

        if ((capital + amount) < 0) {
        	capital = 0;
        } else {
        	capital += amount;
        }
        return capital;
	}
	
	public boolean sellOwnedSquare(Squares square) {
		if (!(square instanceof Own owned) || !ownedSquares.contains(square)) 
			return false;
		changeOfCapital(owned.getPurchasePrice());
		owned.setOwner(null);
		return ownedSquares.remove(owned);
	}
	
	public void autoSellProperties(int capitalRequired) {
		int Capitalreceive = 0;
		int Totalownedproperty = ownedSquares.size();
		
		ownedSquares.sort(Comparator.comparingInt(Own::getPurchasePrice));
		
		for	(int i = 0; i < Totalownedproperty; i++) {
			if (Capitalreceive >=  capitalRequired) return;
			Own owned = ownedSquares.get(0);
			sellOwnedSquare((Squares) owned);
			Capitalreceive += owned.getPurchasePrice();
		}
		
		if (Capitalreceive < capitalRequired ) {
			setBankrupt(true);
		}
	}
	
	public void addProperty(PropertySquares property) {
		ownedProperty.add(property);
    }
	
	public int getNumofOwnedRail() {
		int numOfOwnedRails = 0;
		
		for	(Own owned: ownedSquares) {
			if (owned instanceof RailroadSquare) {
				numOfOwnedRails++;
			}
		}
		return numOfOwnedRails;
	}
	
	public int getNumOfOwnedUtilities() {
		int numOfOwnedUtilities = 0;
		for (Own owned: ownedSquares) {
			if (owned instanceof UtilitySquare) {
				numOfOwnedUtilities++;
			}
		}
		return numOfOwnedUtilities;
	}
	
	@Override
	public String toString() {
		return name + " ";
	}
}
