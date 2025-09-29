package player;

import java.util.ArrayList;
import java.util.List;

public class Player{
	private String name;
	private int pos;
	private int capital = 1500;
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
	
	public void pay(int amount) {
		capital -= amount;
		if (capital < 0) {
			System.out.printf("%s lose becuase of going bankruptcy", name);
		}
	}
}
