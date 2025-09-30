package cardeffect;

import player.Player;

public class AddAmountEffect implements CardEffect{
	private int amount;
	
	public AddAmountEffect(int amount) {
		this.amount = amount;
	}
	
	@Override
	public void execute(Player player) {
		System.out.println(player + " earned $" + player.earn(amount));
	}
}
