package player;

import java.util.ArrayList;
import java.util.List;

//Player info initialize
public class Player{
	private String name;
	private int pos;
	private int capital = 1500;
	//store all properties for player that bought
	private List<Property> ownedProperty = new ArrayList<>();
	private boolean Jail = false;
	
	public Player(String name) {
		this.name = name;
		this.pos = 0;
	}
	
	public void move(int steps) {
		int newpos = (pos + steps) % Board.getInstance().size();
		if (newpos < pos) {
			capital += 200;
		}
	}
	
	public int pay(int amount) {
		capital -= amount;
		if (capital < 0) {
			System.out.printf("%s lose becuase of going bankruptcy", name);
			return 0;
		}
		return capital;
	}
	
	public int earn(int amount) {
		capital += amount;
		return capital;
	}
	
	@Override
	public String toString() {
		return name + " ";
	}
}
