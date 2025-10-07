package application.modal.cardeffect;

import application.modal.player.Player;

/**
 * Concrete strategy for releasing a player from jail.
 * Sets the player's jail status to false without changing their position.
 */
public class GetOutOfJailEffect implements CardEffect {

    @Override
    public void execute(Player player) {
        player.setJail(false);
    }
}