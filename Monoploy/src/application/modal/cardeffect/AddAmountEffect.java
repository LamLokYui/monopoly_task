package application.modal.cardeffect;

import application.modal.player.Player;

//Earned Effect
public class AddAmountEffect implements CardEffect{
	private int amount;
	
	public AddAmountEffect(int amount) {
		this.amount = amount;
	}
	
	@Override
	public void execute(Player player) {
		System.out.println(player + " earned $" + player.changeOfCapital(amount));
	}
}
