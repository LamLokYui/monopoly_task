package application.modal.dice;

import java.util.Random;

public class Dice {
	private final Random random;
	private int dice1;
	private int dice2;
	
	public Dice() {
		this.random = new Random();
	}
	
	public Dice(long seed) {
		this.random = new Random(seed);
	}
	
	public void roll () {
		dice1 = random.nextInt(6)+1;
		dice2 = random.nextInt(6)+1;
	}

	public int getDieRollTotal() {
		return dice1+dice2;
	}
}