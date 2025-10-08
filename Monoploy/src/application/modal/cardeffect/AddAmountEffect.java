package application.modal.cardeffect;

import application.modal.player.Player;

/**
 * Concrete strategy for adding money to a player.
 * Uses Player.addMoney for consistency with financial operations.
 */
public class AddAmountEffect implements CardEffect {
    private final int amount;

    public AddAmountEffect(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute(Player player) {
        player.addMoney(amount);
    }

    // For testability: Getter for amount
    public int getAmount() {
        return amount;
    }
}