package application.modal.cardeffect;

import application.modal.player.Player;

/**
 * Concrete strategy for paying (subtracting) money from a player.
 * Uses Player.addMoney with negative amount for consistency.
 */
public class PayAmountEffect implements CardEffect {
    private final int amount;

    public PayAmountEffect(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute(Player player) {
        player.addMoney(-amount);
    }

    // For testability: Getter for amount
    public int getAmount() {
        return amount;
    }
}