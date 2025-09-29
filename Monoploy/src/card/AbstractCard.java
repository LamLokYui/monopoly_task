package card;

import cardeffect.CardEffect;
import player.Player;

public abstract class AbstractCard implements Card{
	private String description;
	private CardEffect effect;
	
	public AbstractCard	(String description, CardEffect effect) {
		this.description = description;
		this.effect = effect;
	}
	
	@Override
	public void apply (Player player) {
		System.out.println(description);
		effect.execute(player);
	}
	
	@Override
	public String getDescription() {
		return description;
	}
}
