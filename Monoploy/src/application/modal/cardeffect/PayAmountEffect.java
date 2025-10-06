package application.modal.cardeffect;

import application.modal.player.Player;

//Payment Effect
public class PayAmountEffect implements CardEffect{
	private int amount;
	
	public PayAmountEffect(int amount) {
		this.amount = amount;
	}
	
	@Override
	public void execute(Player player) {
		System.out.println(player + " paid $" + player.changeOfCapital(amount));
	}
}
